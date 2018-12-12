import java.util.Scanner;
import java.util.*;

public class Main {
  public static void main(String[] args) {
      List<String> input = readStdin();
      int answer = solution(input);
      System.out.println("ANSWER: " + answer);
  }

  static List<String> readStdin() {
    List<String> ret = new ArrayList<String>();
    Scanner input = new Scanner(System.in);
    while (input.hasNext()) {
      ret.add(input.nextLine());
    }

    return ret;
  }

  static char find(Map<Integer, Character> state, int index, Map<String, Character> maps) {
    String s = "";

    for (int i = index - 2; i <= index + 2; ++i) {
      s += state.getOrDefault(i, '.');
    }

    if (!maps.containsKey(s)) {
      return '.';
    }
    return maps.get(s);
  }

  static void print(int g, Map<Integer, Character> state) {
    int range = state.size();
    String s = "";
    for (Map.Entry<Integer, Character> entry : state.entrySet()) {
      s += entry.getValue();
    }
    System.err.println(Integer.toString(g) + ": " + s);
  }

  static int solution(List<String> input) {
    String initialState = input.get(0).substring(15);
    Map<String, Character> maps = new HashMap<>();
    Map<Integer, Character> state = new TreeMap<>();
    for (int i = 0; i < initialState.length(); i++) {
      state.put(i, initialState.charAt(i));
    }

    for (int i = 2; i < input.size(); ++i) {
      String s = input.get(i);
      maps.put(s.substring(0,5), s.charAt(s.length() - 1));
    }

    print(0, state);

    for (int i = 1; i <= 20; i++) {
      Map<Integer, Character> newState = new TreeMap<>();
      int min = 0;
      int max = 0;
      for (Map.Entry<Integer, Character> entry : state.entrySet()) {
        newState.put(entry.getKey(), find(state, entry.getKey(), maps));
        min = Math.min(entry.getKey(), min);
        max = Math.max(entry.getKey(), max);
      }

      // Expand side (if necessary)
      for (int j = 1; j <= 3; j++) {
        newState.put(min-j, find(state, min-j, maps));
        newState.put(max+j, find(state, max+j, maps));

      }

      state = newState;
    }

    int s = 0;
    for (Map.Entry<Integer, Character> entry : state.entrySet()) {
      if (entry.getValue() == '#') {
        s += entry.getKey();
      }
    }

    return s;
  }
}
