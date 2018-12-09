#include <iostream>
#include <algorithm>
#include <vector>
#include <sstream>
#include <string>
#include <list>

long solution(long players, long totalMarbles) {
  std::vector<unsigned long long> playerScores(players);
  std::list<long> marbles;
  marbles.push_front(0);

  std::cerr << "PLAYERS = " << players << " | TOTAL = " << totalMarbles << std::endl;
  auto cm = marbles.begin();
  for (long i = 1; i < totalMarbles; ++i) {
    if (i % 20000 == 0) {
      float f = (float)i / (float)totalMarbles * 100.0f;
      std::cerr << i << "/" << totalMarbles << " = " << f << std::endl;
    }

    if (i % 23 == 0) {
      playerScores[i % players] += i;
      for (int i = 0; i < 7; ++i) {
        if (cm == marbles.begin()) cm = marbles.end();
        cm--;
      }

      playerScores[i % players] += *cm;
      cm = marbles.erase(cm);
    } else {
      for (int i = 0; i < 2; ++i) {
        if (cm == marbles.end()) cm = marbles.begin();
        cm++;
      }
      cm = marbles.insert(cm, i);
    }
  }

  // Find max
  unsigned long long m = 0;
  for (auto i : playerScores) {
    m = std::max(m, i);
  }
  return m;
}

int main() {
  long players, totalMarbles;
  scanf("%ld players; last marble is worth %ld polongs", &players, &totalMarbles);
  long ans = solution(players, totalMarbles * 100);

  std::cerr << "ANSWER: " <<  ans << std::endl;
  return 0;
}
