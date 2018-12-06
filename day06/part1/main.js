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

function solution(input) {
  var points = getPoints(input);
  var w = 0;
  var h = 0;

  for (var i = 0; i < points.length; i++) {
    w = Math.max(w, points[i][0]);
    h = Math.max(h, points[i][1]);
  }

  w++;
  h++;

  var grid = new Array(w);
  for (var i = 0; i < w; i++) {
    grid[i] = new Array(h);
    for (var j = 0; j < h; j++) {
      grid[i][j] = {
        id: -1,
        depth: 10000000,
      };
    }
  }

  var frontier = [];
  for (var i = 0; i < points.length; i++) {
    frontier.push({
      x: points[i][0],
      y: points[i][1],
      id: i,
      depth: 0,
    });
  }

  while (frontier.length != 0) {
    var f = frontier.shift();
    if (grid[f.x][f.y].id == -1 || grid[f.x][f.y].depth > f.depth) {
      grid[f.x][f.y].id = f.id;
      grid[f.x][f.y].depth = f.depth;
    } else if (grid[f.x][f.y].depth == f.depth && grid[f.x][f.y].id != f.id) {
      grid[f.x][f.y].id = -2;
      continue;
    } else {
      continue;
    }

    if (f.x > 0) {
      frontier.push({
        x: f.x-1,
        y: f.y,
        id: f.id,
        depth: f.depth+1,
      })
    }

    if (f.y > 0) {
      frontier.push({
        x: f.x,
        y: f.y-1,
        id: f.id,
        depth: f.depth+1,
      })
    }

    if (f.x < grid.length-1) {
      frontier.push({
        x: f.x+1,
        y: f.y,
        id: f.id,
        depth: f.depth+1,
      })
    }

    if (f.y < grid[0].length-1) {
      frontier.push({
        x: f.x,
        y: f.y+1,
        id: f.id,
        depth: f.depth+1,
      })
    }
  }

  var counts = new Array(points.length).fill(0);
  var boundary = new Array(points.length).fill(true);
  for (var i = 0; i < grid.length; i++) {
    for (var j = 0; j < grid[i].length; j++) {
      if (grid[i][j].id == -2) {
        continue;
      }
      counts[grid[i][j].id]++;
      if (i == 0 || i == grid.length-1 || j == 0 || j == grid[i].length-1) {
        boundary[grid[i][j].id] = false;
      }
    }
  }

  var m = 0;
  for (var i = 0; i < counts.length; i++) {
    if (boundary[i]) {
      m = Math.max(m, counts[i]);
    }
  }

  return m;
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
