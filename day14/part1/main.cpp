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

void print(std::list<long> recipes) {
  std::stringstream ss;
  for (auto i : recipes) {
    ss << i << " ";
  }
  std::cout << ss.str() << std::endl;
}

std::string solution(long count) {
  std::list<long> recipes;
  recipes.push_back(3);
  recipes.push_back(7);

  auto elf1 = recipes.begin();
  auto elf2 = recipes.begin();
  FORWARD(recipes, elf2, 1);
  
  while (recipes.size() <= (count + 10)) {
    // print(recipes);
    int a = (*elf1);
    int b = (*elf2);
    int combine = a + b;
    if (combine >= 10) {
      recipes.push_back(1);
    }
    recipes.push_back(combine % 10);
    FORWARD(recipes, elf1, a + 1);
    FORWARD(recipes, elf2, b + 1);
  }

  std::stringstream ss;
  auto it = recipes.begin();
  FORWARD(recipes, it, count);
  for (int i = 0; i < 10; ++i) {
    ss << *it;
    FORWARD(recipes, it, 1);
  }

  return ss.str();
}

int main() {
  long count;
  std::cin >> count;
  std::string ans = solution(count);

  std::cerr << "ANSWER: " <<  ans << std::endl;
  return 0;
}
