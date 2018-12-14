#include <iostream>
#include <algorithm>
#include <sstream>
#include <string>
#include <list>

#define FORWARD(list, it, n) \
  for (int iterFor = 0; iterFor < n; ++iterFor) { \
    it++; \
    if (it == list.end()) { it = list.begin(); } \
  }

void print(std::list<int> recipes) {
  std::stringstream ss;
  for (auto i : recipes) {
    ss << i;
  }
  std::cout << ss.str() << "\n" << std::endl;
}

int check(std::list<int> endTrack, std::list<int>& input) {
  std::list<int> forward = endTrack;
  endTrack.pop_front();
  forward.pop_back();

  if (input == endTrack) {
    return 0;
  } else if (input == forward) {
    return 1;
  }
  return -1;

}

int solution(std::string count) {
  std::list<int> recipes;
  recipes.push_back(3);
  recipes.push_back(7);
  std::list<int> endTrack = recipes;
  std::list<int> input;
  for (char c : count) {
    input.push_back(c - '0');
  }

  auto elf1 = recipes.begin();
  auto elf2 = recipes.begin();
  FORWARD(recipes, elf2, 1);
  
  int i = 0;
  while (true) {
    if (++i % 50000 == 0) {
      std::cerr << i << " = " << recipes.size() << std::endl;
    }

    int a = (*elf1);
    int b = (*elf2);
    int combine = a + b;
    if (combine >= 10) {
      recipes.push_back(1);
      endTrack.push_back(1);
    }
    recipes.push_back(combine % 10);
    endTrack.push_back(combine % 10);
    FORWARD(recipes, elf1, a + 1);
    FORWARD(recipes, elf2, b + 1);

    if (endTrack.size() > count.size() + 1) {
      endTrack.pop_front();
    }
    if (endTrack.size() > count.size() + 1) {
      endTrack.pop_front();
    }

    int c = check(endTrack, input);
    if (c != -1) {
      // print(recipes);
      return recipes.size() - count.size() - c;
    }
  }

  return -1;
}

int main() {
  std::string count;
  std::cin >> count;
  int ans = solution(count);

  std::cerr << "ANSWER: " <<  ans << std::endl;
  return 0;
}
