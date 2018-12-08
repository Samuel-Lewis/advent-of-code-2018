package main

import java.util.*

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

fun clearNode(graph: MutableMap<Char, MutableList<Char>>, head: Char): MutableMap<Char, MutableList<Char>> {
    graph.remove(head)
    for ((_, value) in graph) {
        value.removeAll{c -> c == head}
    }

    return graph
}

fun solution(input: MutableList<String>): String {
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

    println(graph)

    var sorted = ""
    var roots:MutableList<Char> = findRoots(graph)
    while (!roots.isEmpty()) {
        roots.sort()
        println(graph)

        val head = roots.first()
        roots.removeAt(0)
        sorted += head
        graph = clearNode(graph, head)
        roots = findRoots(graph)
    }

    return sorted
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
    var answer:String = solution(input)
    println("ANSWER: $answer")
}