#include <iostream>
#include <string>
#include <sstream>

int main() {
  long freq = 0;
  long val;

  std::string line;
  while (std::getline(std::cin, line)) {
    std::stringstream ss(line);
    ss >> val;

    freq += val;
  }

  std::cout << freq << std::endl;
  return 0;
}
