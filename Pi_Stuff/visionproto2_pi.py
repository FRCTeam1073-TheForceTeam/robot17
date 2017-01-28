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

# Pixy Python SWIG get blocks example #
print ("Pixy Python SWIG Example -- Get Blocks")
# Initialize Pixy Interpreter thread #
pixy_init()

class Blocks (Structure):
  _fields_ = [ ("type", c_uint),
               ("signature", c_uint),
               ("x", c_uint),
               ("y", c_uint),
               ("width", c_uint),
               ("height", c_uint),
               ("angle", c_uint) ]

blocks = BlockArray(100)
frame  = 0

def someNumber():
    c = raw_input(('Enter Number, Please: '))
    sd.putNumber('UserNumber: ', c)
    #time.sleep(float(c))

def speedNumberLeft(x):
    a = x - 170
    b = float(a) / float(100)
    print('speed', b)
    sd.putNumber('speed2', b)
    
def speedNumberRight(x):
    a = x - 130
    b = float(a) / float(100)
    print('speed', b)
    sd.putNumber('speed2', b)

# Wait for blocks #
while 1:

  count = pixy_get_blocks(100, blocks)

  if count > 0:
    time.sleep(1)
    # Blocks found #
    print 'frame %3d:' % (frame)
    frame = frame + 1
    # find the largest block
    largest_index = 0
    largest_size = 0
    for index in range (0, count):
      print '[BLOCK:%d BLOCK_TYPE=%d SIG=%d X=%3d Y=%3d WIDTH=%3d HEIGHT=%3d]' % (index, blocks[index].type, blocks[index].signature, blocks[index].x, blocks[index].y, blocks[index].width, blocks[index].height)
      if (blocks[index].width * blocks[index].height) > largest_size:
        largest_index = index
        largest_size = blocks[index].width * blocks[index].height
    print 'largest block=%d' % largest_index


    
    # for the largest block, where is it in relation to the center (x=0-280, y=0-200)
    # center is 140, leave 10 pixels as the center, so test 135, 145

    if (blocks[largest_index].x > 170):
      speedNumberLeft(blocks[largest_index].x)
      print 'Going left'
    elif (blocks[largest_index].x < 130):
      speedNumberRight(blocks[largest_index].x)
      print 'Going right'
    else:
      sd.putNumber('speed2', 0)
      print 'Centered'
