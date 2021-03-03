from networktables import NetworkTables as NT
import logging

logging.basicConfig(level=logging.DEBUG)

#from os.path import dirname, join
#filename = join(dirname(__file__), "filename.txt")

#https://robotpy.readthedocs.io/projects/pynetworktables/en/stable/examples.html
#https://chaquo.com/chaquopy/doc/current/android.html

ip="10.85.85.2"

NT.initialize(server=ip)

sd = NT.getTable("SmartDashboard")
nt = NT.getTable("NT Viewer")

def valueChanged(key, value, isNew):
    print("valueChanged: key: '%s'; value: %s; isNew: %s" % (key, value, isNew))

NT.addEntryListener(valueChanged)

def run():
    return "123IDK"