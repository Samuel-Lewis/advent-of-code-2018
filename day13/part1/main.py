#!/usr/bin/env python3
import sys
import re
import time

class Cart:
  LEFT = 0
  STRAIGHT = 1
  RIGHT = 2

  NORTH = 0
  EAST = 1
  SOUTH = 2
  WEST = 3

  ICONDIR = {'^': NORTH, '>': EAST, 'v': SOUTH, '<': WEST}
  ICONTRACK = {'^': '|', '>': '-', 'v': '|', '<': '-'}

  CHANGE = {
    ('\\', NORTH): WEST,
    ('/',  NORTH): EAST,
    ('|',  NORTH): NORTH,

    ('\\', SOUTH): EAST,
    ('/',  SOUTH): WEST,
    ('|',  SOUTH): SOUTH,

    ('\\', EAST): SOUTH,
    ('/',  EAST): NORTH,
    ('-',  EAST): EAST,

    ('\\', WEST): NORTH,
    ('/',  WEST): SOUTH,
    ('-',  WEST): WEST,
  }

  JUNCTURN = {
    (NORTH, LEFT): WEST,
    (NORTH, STRAIGHT): NORTH,
    (NORTH, RIGHT): EAST,

    (SOUTH, LEFT): EAST,
    (SOUTH, STRAIGHT): SOUTH,
    (SOUTH, RIGHT): WEST,

    (EAST, LEFT): NORTH,
    (EAST, STRAIGHT): EAST,
    (EAST, RIGHT): SOUTH,

    (WEST, LEFT): SOUTH,
    (WEST, STRAIGHT): WEST,
    (WEST, RIGHT): NORTH,
  }

  def __init__(self, x, y, icon):
    self.x = x
    self.y = y
    self.dir = Cart.ICONDIR[icon]
    self.nextTurn = Cart.LEFT

  def move(self):
    if self.dir == Cart.NORTH:
      self.y -= 1
    elif self.dir == Cart.SOUTH:
      self.y += 1
    elif self.dir == Cart.WEST:
      self.x -= 1
    elif self.dir == Cart.EAST:
      self.x += 1

  def orient(self, piece):
    if piece == '+':
      self.dir = Cart.JUNCTURN[(self.dir, self.nextTurn)]
      self.nextTurn = (self.nextTurn + 1) % 3
    else:
      self.dir = Cart.CHANGE[(piece, self.dir)]

def solution(track):
  carts = []
  for y, line in enumerate(track):
    for x, c in enumerate(line):
      if c in Cart.ICONDIR:
        carts.append(Cart(x, y, c))
        track[y][x] = Cart.ICONTRACK[c]

  i = 0
  while True:
    i += 1
    carts = sorted(carts, key = lambda c: (c.y, c.x))
    for c in carts:
      c.move()
      c.orient(track[c.y][c.x])
      for d in carts:
        if c != d and c.x == d.x and c.y == d.y:
          print("CRASH!", i)
          return str(c.x) + "," + str(c.y)
  return (0,0)

def readStdin():
  lines = []
  for line in sys.stdin:
    lines.append(list(line[:-1]))
  return lines

def main():
  lines = readStdin()
  answer = solution(lines)
  print("ANSWER:", answer)

if __name__ == "__main__":
  main()
