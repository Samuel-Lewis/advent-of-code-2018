#!/usr/bin/env python3
import sys
import re
import time

def prettyPrint(pos, t):
  minx = min(x + (t * vx) for (x, y, vx, vy) in pos)
  miny = min(y + (t * vy) for (x, y, vx, vy) in pos)
  maxx = max(x + (t * vx) for (x, y, vx, vy) in pos)
  maxy = max(y + (t * vy) for (x, y, vx, vy) in pos)

  w = maxx - minx + 1
  h = maxy - miny + 1

  display = [[' '] * w for j in range(h)]
  print("\nTIME =", t)
  for (x, y, vx, vy) in pos:
    xx = x + (t * vx) - minx
    yy = y + (t * vy) - miny
    display[yy][xx] = '#'

  for d in display:
    print(''.join(d))

def solution(pos):
  print("FINDING MIN/MAX")
  mint = 0
  mina = 100000000
  for t in range(20000):
    miny = min(y + (t * vy) for (x, y, vx, vy) in pos) - 1
    maxy = max(y + (t * vy) for (x, y, vx, vy) in pos) - 1
    a = maxy - miny
    if a < mina:
      mina = a
      mint = t

  print("PRINT RANGE")
  window = 1
  for t in range(mint-window, mint+window):
    prettyPrint(pos, t)
    time.sleep(0.5)

def readStdin():
  input = []
  for line in sys.stdin:
    v = line.rstrip()
    v = re.findall(r'-?\d+', v)
    v = list(map(int, v))
    input.append(v)
  return input

def main():
  input = readStdin()
  solution(input)

if __name__ == "__main__":
    main()
