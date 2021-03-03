package edu.quick.frc.ntviewerpython;

import android.content.Context;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class nt_utils {
    Python py;
    PyObject module;

    nt_utils(Context context){
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
        py = Python.getInstance();
        module = py.getModule("nt_utils");
    }

    String run(){
        return module.callAttr("run").toString();
    }
}
