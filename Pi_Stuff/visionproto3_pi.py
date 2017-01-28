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

#sd = NetworkTables.getTable("VisionTable")
sd = NetworkTables.getTable("SmartDashboard")

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


    
def findCenterDist(blockCenter):
    b = blockCenter - imageCenter
    print '[centerDist:%d]' % (b)
    sd.putNumber('centerDist', b)

# Wait for blocks #
while 1:

  count = pixy_get_blocks(2, blocks)

  if count > 0:
    time.sleep(.1)
    # Blocks found #
    print 'frame %3d:' % (frame)
    frame = frame + 1
    # find the largest block
#    largest_index = 0
#    largest_size = 0
    for index in range (0, count):
       print '[BLOCK:%d BLOCK_TYPE=%d SIG=%d X=%3d Y=%3d WIDTH=%3d HEIGHT=%3d]' % (index, blocks[index].type, blocks[index].signature, blocks[index].x, blocks[index].y, blocks[index].width, blocks[index].height)
#      if (blocks[index].width * blocks[index].height) > largest_size:
#        largest_index = index
#        largest_size = blocks[index].width * blocks[index].height
#    print 'largest block=%d' % largest_index
    blockCenter = float(blocks[1].x + blocks[1].width + blocks[0].x) / 2
    print('blockCenter', blockCenter)
    findCenterDist(blockCenter)
    
    
    # for the largest block, where is it in relation to the center (x=0-280, y=0-200)
    # center is 140, leave 10 pixels as the center, so test 135, 145
# Old Code (Don't Delete)
#    if (blocks[largest_index].x > 170):
#      speedNumberLeft(blocks[largest_index].x)
#      print 'Going left'
#    elif (blocks[largest_index].x < 130):
#      speedNumberRight(blocks[largest_index].x)
#      print 'Going right'
#    else:
#      sd.putNumber('speed2', 0)
#      print 'Centered'


