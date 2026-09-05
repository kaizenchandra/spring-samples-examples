package unit;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Palindrome {

    public boolean isPalindrome(String str) {
        StringBuilder reverse = new StringBuilder(str).reverse();
        return str.contentEquals(reverse);
    }

    public boolean isPalindrome2(String s) {
        String str = s.toLowerCase().replaceAll("\\s+", "");
        int strLen = str.length();
        return IntStream.range(0, strLen)
                .allMatch(i -> str.charAt(i) == str.charAt(strLen - i - 1));
    }

    public boolean isPalindrome3(String s) {
        String reversed = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.reverse(list);
                            return list.stream()
                                    .map(String::valueOf)
                                    .collect(Collectors.joining());
                        }
                ));
        return reversed.equals(s);
    }

    

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        boolean racecar = palindrome.isPalindrome2("racecar");
        System.out.println("isPalindrome: " + racecar);
        boolean radar = palindrome.isPalindrome3("radar");
        System.out.println("isPalindrome: " + radar);
    }
}
