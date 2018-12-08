package main

import java.util.*

var DELAY:Int = 60
var WORKERS:Int = 5
var ORDER:String = "FHICMRTXYDBOAJNPWQGVZUEKLS"

fun findRoots(graph: Map<Char, MutableList<Char>>): MutableList<Char> {
    var counts: MutableMap<Char, Int> = mutableMapOf<Char, Int>()
    for ((_, value) in graph) {
        for (c in value) {
            counts[c] = (counts[c] ?: 0) + 1
        }
    }

    var roots = mutableListOf<Char>()
    for ((key , _) in graph) {
        if (key !in counts) {
            roots.add(key)
        }
    }
    return roots
}

fun findFirst(jobs: MutableList<Char>, pending: MutableSet<Char>, graph: MutableMap<Char, MutableList<Char>>): Char {
    var roots = findRoots(graph)
    for (c in jobs) {
        if (c in roots && c !in pending) {
            return c
        }
    }
    return '.'
}

fun clean(head: Char, graph: MutableMap<Char, MutableList<Char>>) {
    graph.remove(head)
    for ((_, value) in graph) {
        value.removeAll{c -> c == head}
    }
}

fun solution(input: MutableList<String>): Int {
    var graph = mutableMapOf<Char, MutableList<Char>>()
    for (line in input) {
        var a = line[5]
        var b = line[36]
        if (!graph.containsKey(a)) {
            graph[a] = mutableListOf<Char>()
        }
        if (!graph.containsKey(b)) {
            graph[b] = mutableListOf<Char>()
        }
        graph[a]!!.add(b)
    }
    var jobs:MutableList<Char> = ORDER.toMutableList()

    var time:Int = 0;
    var pending: MutableSet<Char> = mutableSetOf<Char>()
    var workers: MutableList<Pair<Char, Int>> = mutableListOf<Pair<Char, Int>>()
    for (i in 1..WORKERS) {
        workers.add(Pair('.',0))
    }

    while (!pending.isEmpty() || !jobs.isEmpty()) {
        workers = workers.map{
            var w = Math.max(0, it.second - 1)
            var c = it.first
            if (w <= 0 && c != '.') {
                // finish job
                pending.remove(c)
                clean(c, graph)
                c = '.'
            }

            // take new job if available
            var first = findFirst(jobs, pending, graph)
            if (first != '.' && c == '.') {
                // take job
                c = first
                w = first.toInt() - 'A'.toInt() + 1 + DELAY
                pending.add(first)
                jobs.remove(first)
            }
            return@map Pair(c, w)
        }.toMutableList<Pair<Char, Int>>()

        println(workers)
        if (pending.isEmpty() && jobs.isEmpty()) {
            break;
        }
        time++
    }

    return time-1
}

fun getStdin(): MutableList<String> {
    var input:MutableList<String> = mutableListOf<String>()
    val reader = Scanner(System.`in`)

    while (reader.hasNext()) {
        input.add(reader.nextLine())
    }
    return input
}

fun main(args : Array<String>) {
    var input:MutableList<String> = getStdin()
    var answer:Int = solution(input)
    println("ANSWER: $answer")
}