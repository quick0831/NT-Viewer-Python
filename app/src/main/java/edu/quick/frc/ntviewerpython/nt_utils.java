package edu.quick.frc.ntviewerpython;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

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

    String getSubTables(){
        return module.callAttr("getSubTables", nt).toString();
    }

    String getKeys(){
        return module.callAttr("getKeys", nt).toString();
    }

    Entry getEntry(){
        return new Entry(module.callAttr("getEntry", nt));
    }

    class Entry{
        private final PyObject entry;
        public String type;
        public Entry(PyObject e) {
            entry = e;
            type = module.callAttr("getType", e).toString();
        }

        public String getValue(){
            return module.callAttr("getValueString", entry).toString();
        }
    }
}
