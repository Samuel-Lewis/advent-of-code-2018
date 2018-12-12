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

function powerRange(cells, x, y, s) {
  var p = 0;
  for (var i = x; i < x + s; i++) {
    for (var j = y; j < y + s; j++) {
      p += cells[i][j];
    }
  }
  return p;
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
  var bestS = [0, 0, 0];
  for (var s = 0; s < w; s++) {
  console.log("bestP = " + bestP + " | s = " + s);
  console.log("\t best = " + bestS);
    for (var i = 1; i <= w-s; i++) {
      for (var j = 1; j <= w-s; j++) {
        var p = powerRange(cells, i, j, s);
        if (p > bestP) {
          bestP = p;
          bestS = [i, j, s];
        }
      }
    }
  }

  return bestS;
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
