#!/usr/bin/env node
const fs = require("fs");

function getPoints(input) {
  var points = [];
  input = input.split("\n");
  for (var i = 0; i < input.length; i++) {
    var l = input[i].split(", ");
    if (l.length == 1) {
      continue;
    }

    points.push(l.map(Number));
  }
  return points;
}

function dist(x1, y1, x2, y2) {
  return Math.abs(x1-x2) + Math.abs(y1-y2);
}

function solution(input) {
  var points = getPoints(input);
  var w = 0;
  var h = 0;

  for (var i = 0; i < points.length; i++) {
    w = Math.max(w, points[i][0]);
    h = Math.max(h, points[i][1]);
  }

  var count = 0;
  for (var x = 0; x < w; x++) {
    var s = "";
    for (var y = 0; y < h; y++) {
      var d = 0;
      for (var i = 0; i < points.length; i++) {
        d += dist(x, y, points[i][0], points[i][1]);       
      }

      if (d < 10000) {
        count++;
        s += "#";
      } else {
        s += ".";
      }
    }
    console.log(s);
  }

  return count;
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
