#!/usr/bin/env node
const fs = require("fs");

function power(x, y, serial) {
  var rackID = x + 10;
  var power = (rackID * y) + serial;
  power *= rackID;
  power = power < 100 ? 0 : parseInt(''+power / 100) % 10;
  power -= 5;
  return power;
}

function solution(input) {
  var serial = parseInt(input);
  var w = 300;
  console.log(serial);

  var cells = new Array(w+1);
  for (var i = 1; i <= w; i++) {
    cells[i] = new Array(w+1);
    for (var j = 1; j <= w; j++) {
      cells[i][j] = power(i ,j, serial);
    }
  }

  var bestP = -5;
  var bestS = [0, 0];
  for (var i = 1; i <= w-2; i++) {
    for (var j = 1; j <= w-2; j++) {
      var p = 0;
      p += cells[i][j+0] + cells[i+1][j+0] + cells[i+2][j+0];
      p += cells[i][j+1] + cells[i+1][j+1] + cells[i+2][j+1];
      p += cells[i][j+2] + cells[i+1][j+2] + cells[i+2][j+2];
      if (p > bestP) {
        bestP = p;
        bestS = [i, j];
      }
    }
  }

  return "" + bestS[0] + "," + bestS[1];
}

function readStdin() {
  return fs.readFileSync("/dev/stdin", "utf-8");
}

function main() {
  input = readStdin();
  answer = solution(input);
  console.log("ANSWER: " + answer);
}

main();
