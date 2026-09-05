package unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import javax.swing.plaf.PanelUI;

public class JunitTest {

    private Palindrome palindrome = new Palindrome();

    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "able was I ere I saw elba"})
    public void palindromeTest(String candidate) {
        Assertions
                .assertTrue(palindrome.isPalindrome(candidate));
    }
}
