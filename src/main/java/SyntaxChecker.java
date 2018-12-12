import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.Stack;

public class SyntaxChecker {
    public boolean check(String stringToCheck) {
        int lengthOfStringToCheck = stringToCheck.length();
        if (lengthOfStringToCheck == 0) {
            return true;
        } else {
            Stack<Character> leftBracketsInString = new Stack<>();
            for (int i = 0; i < lengthOfStringToCheck; i++) {
                char bracketCandidate = stringToCheck.charAt(i);
                if (Brackets.isLeftBracket(bracketCandidate)) {
                    leftBracketsInString.push(bracketCandidate);
                } else if (Brackets.isRightBracket(bracketCandidate)) {
                    if (leftBracketsInString.isEmpty()) {
                        return false;
                    } else {
                        if (!(bracketCandidate == Brackets.getBracketPartner(leftBracketsInString.pop()))) {
                            return false;
                        }
                    }
                }
            }
            return leftBracketsInString.isEmpty();
        }
    }

    private static class Brackets {
        private static BidiMap<Character, Character> bracketPartners;

        static {
            bracketPartners = new DualHashBidiMap<>();
            bracketPartners.put('{', '}');
            bracketPartners.put('(', ')');
            bracketPartners.put('[', ']');
            bracketPartners.put('<', '>');
        }

        static boolean isLeftBracket(char bracketCandidate) {
            return bracketPartners.containsKey(bracketCandidate);
        }

        static boolean isRightBracket(char bracketCandidate) {
            return bracketPartners.containsValue(bracketCandidate);
        }

        static Character getBracketPartner(char bracket) {
            if (bracketPartners.containsKey(bracket)) {
                return bracketPartners.get(bracket);
            } else if (bracketPartners.containsValue(bracket)) {
                return bracketPartners.getKey(bracket);
            }
            throw new IllegalArgumentException("input is not a bracket: " + bracket);
        }
    }
}