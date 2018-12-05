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

    Collections.sort(ret);
    return ret;
  }

  static int reduce(String input, char lower) {
    input = input.replace(Character.toString(lower),"");
    input = input.replace(Character.toString(Character.toUpperCase(lower)),"");
    StringBuilder line = new StringBuilder(input);
    Boolean changed = false;

    do {
      changed = false;

      for (int i = 0; i < line.length()-1; i++) {
        if ((line.charAt(i) != line.charAt(i+1)) &&
            (Character.toLowerCase(line.charAt(i)) == Character.toLowerCase(line.charAt(i+1)))) {
          line.delete(i, i+2);
          i += 2;
          changed = true;
        }
      }
    } while (changed);
    return line.length();
  }

  static int solution(List<String> input) {
    String line = input.get(0);
    System.err.println(line);
    int min = line.length();
    
    for (char alpha = 'a'; alpha <= 'z'; alpha++) {
      int r = reduce(line, alpha);
      min = Math.min(r,min);
    }

    return min;
  }
}
