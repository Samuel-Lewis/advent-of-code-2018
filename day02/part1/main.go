package main

import (
	"bufio"
	"fmt"
	"os"
)

func count(in string) (bool, bool) {
	count := make(map[rune]int)
	has2 := false
	has3 := false

	for _, char := range in {
		count[char]++
	}

	for _, val := range count {
		if val == 2 {
			has2 = true
		}

		if val == 3 {
			has3 = true
		}
	}

	return has2, has3
}

func solution(input []string) int {
	var count2 int
	var count3 int

	for _, val := range input {
		h2, h3 := count(val)
		if h2 {
			count2++
		}

		if h3 {
			count3++
		}
		fmt.Println(val, h2, h3)
	}

	return count2 * count3
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
