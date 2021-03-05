package edu.quick.frc.ntviewerpython;

import android.app.Activity;
import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class nt_utils {
    Python py;
    PyObject module, nt;

    nt_utils(Context context){
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        py = Python.getInstance();
        module = py.getModule("nt_utils");

        nt = module.callAttr("getNT");
    }

    PyObject getTable(String key){
        return module.callAttr("getTable", key);
    }

    List<String> getTables() {
        String str = module.callAttr("getTables").toString();
        return stringToList(str);
    }

    List<String> getSubTables(PyObject table) {
        String str = module.callAttr("getSubTables", table).toString();
        return stringToList(str);
    }

    List<String> getKeys(PyObject table){
        String str = module.callAttr("getKeys", table).toString();
        return stringToList(str);
    }

    Entry getEntry(PyObject table, String key){
        return new Entry(module.callAttr("getEntry", table, key), key);
    }

    class Entry{
        private final PyObject entry;
        public final String key;
        public Entry(PyObject e, String k) {
            entry = e;
            key = k;
        }

        public String getType(){
            return module.callAttr("getType", entry).toString();
        }

        public String getValue(){
            return module.callAttr("getValueString", entry).toString();
        }
    }

    static class Pwd{
        List<String> dir = new ArrayList<>();
        nt_utils nt;
        MainActivity activity;

        public Pwd(MainActivity activity, nt_utils nt) {
            this.nt = nt;
            this.activity = activity;
        }

        boolean isRoot(){
            return dir.size() == 0;
        }

        String getFullPath(){
            StringBuilder path = new StringBuilder();
            for(String s: dir)
                path.append("/").append(s);
            return path.toString();
        }

        void cdBack(){
            dir.remove(dir.size()-1);
            activity.updateData();
        }

        void cd(String subTable){
            dir.add(subTable);
            activity.updateData();
        }

        PyObject getTable(){
            return nt.getTable(getFullPath());
        }
    }

    //String[] list = t.replace("[", "").replace("]", "").split(",");
    private List<String> stringToList(String str) {
        try {
            JSONArray jsonArray = new JSONArray(str);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++)
                list.add(jsonArray.getString(i));
            return list;
        } catch (JSONException e) {
            return null;
        }
    }
}
