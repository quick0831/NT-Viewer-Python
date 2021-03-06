# NT Viewer Python

A Simple NetworkTable Viewer made by Quick Chang

FRC Team 8585 in Taiwan

This repository is licensed under MIT Licence

## NT temporary server

For testing only

```python
from networktables import NetworkTables
import time
n = NetworkTables.create()
n.initialize()
sd = n.getTable("SmartDashboard")

s=n.getTable("/SmartDashboard/StupidDashboard")
s.putNumber("a", 1)
s.putString("b", "OAO")

i = 0
while True:
    sd.putNumber("robotTime", i)
    print("robotTime:", sd.getNumber("robotTime", -1))
    time.sleep(1)
    i += 1
```