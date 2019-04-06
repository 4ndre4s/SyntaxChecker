import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class SyntaxCheckerTest {
    @Test
    public void check_empty_true() {
        //arrange
        boolean expectedResult = true;
        String input = "";

        //act
        boolean actualResult = new SyntaxChecker().check(input);

        //assert
        assertThat("empty input is true", actualResult, CoreMatchers.equalTo(expectedResult));
    }

    @Test
    public void check_curlyBracketOpenAndClosed_true() {
        //arrange
        boolean expectedResult = true;
        String input = "{}";

        //act
        boolean actualResult = new SyntaxChecker().check(input);

        //assert
        assertThat("right curly bracket after left curly bracket is true", actualResult,
                CoreMatchers.equalTo(expectedResult));
    }

    @Test
    public void check_curlyBracketOpenAndNotClosed_false() {
        //arrange
        boolean expectedResult = false;
        String input = "{";

        //act
        boolean actualResult = new SyntaxChecker().check(input);

        //assert
        assertThat("left curly bracket without right one is false", actualResult, CoreMatchers.equalTo(expectedResult));
    }

    @Test
    public void check_curlyBracketAndClosedAfterSomeLetters_true() {
        //arrange
        boolean expectedResult = true;
        String input = "{asdf}";

        //act
        boolean actualResult = new SyntaxChecker().check(input);

        //assert
        assertThat("bracket-pair divided by string is true", actualResult, CoreMatchers.equalTo(expectedResult));
    }

    @Test
    public void check_curlyBracketAndNotClosedAfterSomeLetters_false() {
        //arrange
        boolean expectedResult = false;
        String input = "{asdf";

        //act
        boolean actualResult = new SyntaxChecker().check(input);

        //assert
        assertThat("only one left bracket and a string is false", actualResult, CoreMatchers.equalTo(expectedResult));
    }

    @Test
    public void check_curlyBracketClosedBeforeLeftBracket_false() {
        //arrange
        boolean expectedResult = false;
        String input = "}asdf";

        //act
        boolean actualResult = new SyntaxChecker().check(input);

        //assert
        assertThat("only one right bracket and string is false", actualResult, CoreMatchers.equalTo(expectedResult));
    }

    @Test
    public void check_leftCurlyBracketAndRightSquareBracket_false() {
        //arrange
        boolean expectedResult = false;
        String input = "{]";

        //act
        boolean actualResult = new SyntaxChecker().check(input);

        //assert
        assertThat("curly left bracket does not match square right bracket", actualResult,
                CoreMatchers.equalTo(expectedResult));
    }

    @Test
    public void check_angleBracketsDividedByCurlyBrackets_true() {
        //arrange
        boolean expectedResult = true;
        String input = "<{}>";

        //act
        boolean actualResult = new SyntaxChecker().check(input);

        //assert
        assertThat("left and right angle bracket divided by left and right curly bracket", actualResult,
                CoreMatchers.equalTo(expectedResult));
    }
}
