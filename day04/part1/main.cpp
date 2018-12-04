#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <map>
#include <algorithm>

struct Guard {
  int id;
  std::vector<int> asleep;
  int totalSleep;

  Guard(int i) {
    id = i;
    totalSleep = 0;
    asleep = std::vector<int>(60);
  }

  void sleep(int s, int e) {
    for (int i = s; i < e; i++) {
      asleep[i]++;
    }
    totalSleep += e - s;
  }

  int mostAsleep() {
    int max = 0;
    for (int i = 0; i < asleep.size(); i++) {
      if (asleep[i] > asleep[max]) {
        max = i;
      }
    }
    return max;
  }
};


int solution(std::vector<std::string> input) {
  std::map<int, Guard*> guards;
  int lastId = -1;

  for (int i = 0; i < input.size(); i++) {
    std::istringstream iss(input[i]);
    int secStart = 0;
    int secEnd = 0;
    int id;
    std::string action;

    iss.ignore(15);
    iss >> secStart;
    iss.ignore(2);
    iss >> action;
    iss.ignore(2);
    iss >> id;

    if (action == "Guard") {
      lastId = id;
      if (guards.count(id) == 0) {
        guards[id] = new Guard(id);
      }
    } else if (action == "falls") {
      iss = std::istringstream(input[++i]);
      iss.ignore(15);
      iss >> secEnd;

      guards[lastId]->sleep(secStart, secEnd);
    }
  }

  int maxId;
  int maxSleep = -1;
  for (auto const& pair : guards ) {
    if (pair.second->totalSleep > maxSleep) {
      maxId = pair.first;
      maxSleep = pair.second->totalSleep;
    }
  }
  Guard* best = guards[maxId];
  return best->id * best->mostAsleep();
}

std::vector<std::string> readStdin() {
  std::vector<std::string> in;

  std::string line;
  while (std::getline(std::cin, line)) {
    in.push_back(line);
  }
  std::sort(in.begin(), in.end());
  return in;
}

int main() {
  std::vector<std::string> input = readStdin();
  int ans = solution(input);

  std::cout << "ANSWER: " << ans << std::endl;
  return 0;
}
