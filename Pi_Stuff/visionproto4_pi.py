from pixy import *
from ctypes import *
import time
import sys

from networktables import NetworkTables

# To see messages from networktables, you must setup logging
import logging
logging.basicConfig(level=logging.DEBUG)

if len(sys.argv) != 2:
    print("Error: specify an IP to connect to!")
    exit(0)

ip = sys.argv[1]

NetworkTables.initialize(server=ip)

sd = NetworkTables.getTable("VisionTable")
#sd = NetworkTables.getTable("SmartDashboard")

# Pixy Python SWIG get blocks example #
print ("1073 Video Prototype ")
# Initialize Pixy Interpreter thread #
pixy_init()

print ("If this is not finding blocks make sure you start it with 'sudo python'")


class Blocks (Structure):
  _fields_ = [ ("type", c_uint),
               ("signature", c_uint),
               ("x", c_uint),
               ("y", c_uint),
               ("width", c_uint),
               ("height", c_uint),
               ("angle", c_uint) ]

blocks = BlockArray(2)
frame  = 0

imageCenter = 160


def someNumber():
    c = raw_input(('Enter Number, Please: '))
    sd.putNumber('UserNumber: ', c)
    #time.sleep(float(c))

def averageWidth(widthAvg):
    
    print '[AverageWidth:%d]' % (widthAvg)
    sd.putNumber('AverageWidth', widthAvg)

    
def findCenterDist(blockCenter):
    b = blockCenter - imageCenter
    print '[centerDist:%d]' % (b)
    sd.putNumber('centerDist', b)

# Wait for blocks #
while 1:

  count = pixy_get_blocks(2, blocks)

  if count > 0:
    time.sleep(.05)
    # Blocks found #
    print 'frame %3d:' % (frame)
    frame = frame + 1

    for index in range (0, count):
       print '[BLOCK:%d BLOCK_TYPE=%d SIG=%d X=%3d Y=%3d WIDTH=%3d HEIGHT=%3d]' % (index, blocks[index].type, blocks[index].signature, blocks[index].x, blocks[index].y, blocks[index].width, blocks[index].height)

    widthAvg = (blocks[0].width + blocks[1].width) / 2
    blockCenter = float(blocks[1].x + blocks[1].width + blocks[0].x) / 2
    print('blockCenter', blockCenter)
    findCenterDist(blockCenter)
    averageWidth(widthAvg = (blocks[0].width + blocks[1].width) / 2)
