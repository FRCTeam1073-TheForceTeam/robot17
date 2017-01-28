from pixy import *
from ctypes import *
import time
import sys

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

# Wait for blocks #
while 1:

  count = pixy_get_blocks(100, blocks)

  if count > 0:
    time.sleep(1)
    # Blocks found #
    print 'frame %3d:' % (frame)
    frame = frame + 1
    # JR find the largest block
    largest_index = 0
    largest_size = 0
    for index in range (0, count):
      print '[BLOCK:%d BLOCK_TYPE=%d SIG=%d X=%3d Y=%3d WIDTH=%3d HEIGHT=%3d]' % (index, blocks[index].type, blocks[index].signature, blocks[index].x, blocks[index].y, blocks[index].width, blocks[index].height)
      if (blocks[index].width * blocks[index].height) > largest_size:
        largest_index = index
        largest_size = blocks[index].width * blocks[index].height
    print 'largest block=%d' % largest_index
    # for the largest block where i it in realtion to the center (x=0-275, y=0-200)
    # so center area 
    if (blocks[largest_index].x > 140) and :
      print 'Go left'
    else:
      print 'Go right'
