#!/usr/bin/env python
#
# This is a NetworkTables client (eg, the DriverStation/coprocessor side).
# You need to tell it the IP address of the NetworkTables server (the
# robot or simulator).
#
# When running, this will continue incrementing the value 'dsTime', and the
# value should be visible to other networktables clients and the robot.
#
# Run this as follows:
# 
# python testdrive.py 10.10.73.67
#

import sys
import time
from networktables import NetworkTables

# To see messages from networktables, you must setup logging
import logging
logging.basicConfig(level=logging.DEBUG)

if len(sys.argv) != 2:
    print("Error: specify an IP to connect to!")
    exit(0)
    
def someNumber():
    c = raw_input(('Enter Number, Please: '))
    sd.putNumber('UserNumber: ', c)
    #time.sleep(float(c))
    
    
ip = sys.argv[1]

NetworkTables.initialize(server=ip)

sd = NetworkTables.getTable("SmartDashboard")

i = 0
x = 0
while True:
    try:
        x = sd.getNumber('robotTime')
        print('robotTime:', x)
    except KeyError:
        print('robotTime: N/A')

    try:
        print('dsTime:', sd.getNumber('dsTime'))
    except KeyError:
        print('dsTime: N/A')
    x = x + 2
    print('putting robotTime2:', x)
    sd.putNumber('robotTime2', x)
    time.sleep(1)
    i += 1

    someNumber()
    


