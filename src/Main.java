

import java.util.*;

public class Main {
    //Since we don't need to change the values of the Map and the array of strings we can define then as static final.
    private static final Hashtable<Character, Character> unclosed = new Hashtable<Character, Character>();
    private static final String[] testedWords = new String[]{"&:)", ")()(()", "O Fulano (aquele que fez aniversário hoje) perguntou por você.", "((1+1)*2) + (10+2) + (((2-1)-1)*1)"};


    public static boolean isBalancedParentheses(final String s1, final LinkedList<Character> openedParentheses, final Hashtable<Character, Character> unclosed) {
        //verify if the string is empty or null.
        if ((s1 == null) || s1.isEmpty()) {
            return openedParentheses.isEmpty();
        }
        //verify if the first character value in the string is inside the Map unclosed that means it is a parentheses.
        if (unclosed.containsValue(s1.charAt(0))) {
            openedParentheses.add(s1.charAt(0));
            return isBalancedParentheses(s1.substring(1), openedParentheses, unclosed);
        }
        //verify if Map unclosed contains the key of the first character of the s1 string.
        //necessary to put a try catch to handle noSuchElementException because some words can start with the closed parentheses.
        if (unclosed.containsKey(s1.charAt(0))) {
            try {
                if (openedParentheses.getLast() == unclosed.get(s1.charAt(0))) {
                    openedParentheses.removeLast();
                    return isBalancedParentheses(s1.substring(1), openedParentheses, unclosed);
                } else {
                    return false;
                }
            } catch (NoSuchElementException e) {
                return false;
            }
        } else {
            return isBalancedParentheses(s1.substring(1), openedParentheses, unclosed);
        }
    }

    public static void main(final String[] args) {
        unclosed.put(')', '(');
        for (final String words : testedWords) {
            System.out.println("A palavra ou frase: " + words + "e seu resultado é:" + isBalancedParentheses(words, new LinkedList<Character>(), unclosed));
        }
    }
}
