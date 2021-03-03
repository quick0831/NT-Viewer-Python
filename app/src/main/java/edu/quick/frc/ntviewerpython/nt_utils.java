package edu.quick.frc.ntviewerpython;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

public class nt_utils {
    Python py;
    PyObject module;

    nt_utils(){
        py = Python.getInstance();
        module = py.getModule("nt_utils");
    }

    String run(){
        return module.callAttr();
    }
}
