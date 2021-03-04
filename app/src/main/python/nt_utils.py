from networktables import NetworkTables as NT
import settings
import logging

logging.basicConfig(level=logging.DEBUG)

#from os.path import dirname, join
#filename = join(dirname(__file__), "filename.txt")

#https://robotpy.readthedocs.io/projects/pynetworktables/en/stable/examples.html
#https://chaquo.com/chaquopy/doc/current/android.html

#def valueChanged(key, value, isNew):
#    print("valueChanged: key: '%s'; value: %s; isNew: %s" % (key, value, isNew))
#NT.addEntryListener(valueChanged)

# ======================================================================================

ip = settings.ip

NT.initialize(server=ip)

types = {
    NT.EntryTypes.BOOLEAN       : "Boolean",
    NT.EntryTypes.BOOLEAN_ARRAY : "Boolean Array",
    NT.EntryTypes.DOUBLE        : "Number",
    NT.EntryTypes.DOUBLE_ARRAY  : "Number Array",
    NT.EntryTypes.RAW           : "Raw",
    NT.EntryTypes.STRING        : "String",
    NT.EntryTypes.STRING_ARRAY  : "String Array"
}

def getNT(): # Big Table including all the tables
    return NT.getGlobalTable()

def getTable(key):
    return NT.getTable(key)

def getSubTables(table):
    return table.getSubTables()

def getTables():
    return NT.getGlobalTable().getSubTables()

def getKeys(table, types=0):
    return table.getKeys(types)

def getEntry(table, key):
    return table.getEntry(key)

def getValue(entry):
    return entry.value

def getValueString(entry):
    return repr(entry.value)

def getType(entry):
    return types[entry.getType()]
