#!/usr/bin/env python3
import sys
import re

def solution(input):
  matrix = [[0 for x in range(1000)] for y in range(1000)] 
  count = 0

  for line in input:
    for i in range(line[1], line[1] + line[3]):
      for j in range(line[2], line[2] + line[4]):
        matrix[i][j] += 1
        if matrix[i][j] == 2:
          count += 1

  return count

def readStdin():
  input = []
  for line in sys.stdin:
    v = line.rstrip()
    v = re.findall('\d+', line)
    v = list(map(int, v))
    input.append(v)
  return input

def main():
  input = readStdin()
  answer = solution(input)
  print("ANSWER:", answer)

if __name__ == "__main__":
    main()
