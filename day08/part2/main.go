package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func toIntArray(input string) []int {
	a := strings.Split(input, " ")
	b := make([]int, len(a))
	for i, v := range a {
		b[i], _ = strconv.Atoi(v)
	}
	return b
}

func countMetadata(input []int, start int) (int, int) {
	children := input[start]
	metaEntries := input[start+1]
	metaStart := start + 2
	sum := 0
	childrenVals := make([]int, children+1)

	if children != 0 {
		childStart := start + 2
		for c := 1; c <= children; c++ {
			s, cs := countMetadata(input, childStart)
			childrenVals[c] = s
			childStart = cs
		}
		metaStart = childStart
	}
	metaEnd := metaStart + metaEntries

	for i := metaStart; i < metaEnd; i++ {
		s := input[i]
		if children == 0 {
			sum += s
		} else if s < len(childrenVals) {
			sum += childrenVals[s]
		}
	}

	return sum, metaEnd
}

func solution(input []string) int {
	sum, _ := countMetadata(toIntArray(input[0]), 0)
	return sum
}

func readStdin() []string {
	var res []string
	scanner := bufio.NewScanner(os.Stdin)
	for scanner.Scan() {
		res = append(res, scanner.Text())
	}
	return res
}

func main() {
	input := readStdin()
	answer := solution(input)
	fmt.Println("ANSWER:", answer)
}
