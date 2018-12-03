package main

import (
	"bufio"
	"fmt"
	"os"
)

func diff(lhs string, rhs string) int {
	d := byte('.')
	pos := -1
	for i := 0; i < len(lhs); i++ {
		if lhs[i] != rhs[i] {
			if d != '.' {
				return -1
			}
			d = lhs[i]
			pos = i
		}
	}

	return pos
}

func solution(input []string) string {
	for i, val := range input {
		for k := i + 1; k < len(input); k++ {
			pos := diff(input[i], input[k])
			if pos != -1 {
				return (val[0:pos] + val[pos+1:])
			}
		}
	}

	return "NOPE"
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

	fmt.Println("ANSWER: ", answer)
}
