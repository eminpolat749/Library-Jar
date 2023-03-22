package org.csystem;

import org.csystem.ArrayUtil;
import org.csystem.ArrayUtil;

import java.util.Random;

public class StringUtil {
    public static String alphabetTR = "abcçdefgğhıijklmnoöprsştuüvyz";
    public static String alphabetEN = "abcdefghijklmnopqrstuwvxyz";
    public static String alphabetCapitalTR = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ";
    public static String alphabetCapitalEN = "ABCDEFGHIJKLMNOPQRSTUWVXYZ";
    public static String alphabetAllTR = alphabetTR + alphabetCapitalTR;
    public static String alphabetAllEN = alphabetEN + alphabetCapitalEN;

    public static String capitalize(String s)
    {
        return s.isEmpty() ? "" : Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }

    public static String changeCase(String s)
    {
        char [] chars = new char[s.length()];

        for (int i = 0; i < chars.length; ++i) {
            char c = s.charAt(i);

            chars[i] = Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c);
        }

        return String.valueOf(chars);
    }

    public static int countString(String s1, String s2)
    {
        int count = 0;

        for (int i = -1; (i = s1.indexOf(s2, i + 1)) != -1; ++count)
            ;

        return count;
    }

    public static int countStringIgnoreCase(String s1, String s2)
    {
        return countString(s1.toLowerCase(), s2.toLowerCase());
    }


    public static void fillRandomTexts(Random r, String [] texts, int n, String sourceText)
    {
        for (int i = 0; i < texts.length; ++i)
            texts[i] = getRandomText(r, n, sourceText);
    }

    public static void fillRandomTexts(Random r, String [] texts, int min, int bound, String sourceText)
    {
        for (int i = 0; i < texts.length; ++i)
            texts[i] = getRandomText(r, r.nextInt(min, bound), sourceText);
    }

    public static void fillRandomTextsTR(Random r, String [] texts, int n)
    {
        fillRandomTexts(r, texts, n, alphabetAllTR);
    }

    public static void fillRandomTextsTR(Random r, String [] texts, int min, int bound)
    {
        fillRandomTexts(r, texts, min, bound, alphabetAllTR);
    }

    public static void fillRandomTextsEN(Random r, String [] texts, int n)
    {
        fillRandomTexts(r, texts, n, alphabetAllEN);
    }

    public static void fillRandomTextsEN(Random r, String [] texts, int min, int bound)
    {
        fillRandomTexts(r, texts, min, bound, alphabetAllEN);
    }

    public static String getRandomText(Random r, int n, String sourceText)
    {
        char [] c = new char[n];
        int len = sourceText.length();

        for (int i = 0; i < n; ++i)
            c[i] = sourceText.charAt(r.nextInt(len));

        return String.valueOf(c);
    }

    public static String [] getRandomTexts(Random r, int count, int min, int bound, String sourceText)
    {
        String [] texts = new String[count];

        fillRandomTexts(r, texts, min, bound, sourceText);

        return texts;
    }

    public static String [] getRandomTexts(Random r, int count, int n, String sourceText)
    {
        String [] texts = new String[count];

        fillRandomTexts(r, texts, n, sourceText);

        return texts;
    }

    public static String getRandomTextEN(int n)
    {
        return getRandomTextEN(new Random(), n);
    }

    public static String getRandomTextEN(Random r, int n)
    {
        return getRandomText(r, n, alphabetAllEN);
    }

    public static String getRandomTextTR(int n)
    {
        return getRandomTextTR(new Random(), n);
    }

    public static String getRandomTextTR(Random r, int n)
    {
        return getRandomText(r, n, alphabetAllTR);
    }

    public static String [] getRandomTextsTR(Random r, int count, int min, int bound)
    {
        return getRandomTexts(r, count, min, bound, alphabetAllTR);
    }

    public static String [] getRandomTextsTR(Random r, int count, int n)
    {
        return getRandomTexts(r, count, n, alphabetAllTR);
    }

    public static String [] getRandomTextsEN(Random r, int count, int min, int bound)
    {
        return getRandomTexts(r, count, min, bound, alphabetAllEN);
    }

    public static String [] getRandomTextsEN(Random r, int count, int n)
    {
        return getRandomTexts(r, count, n, alphabetAllEN);
    }

    public static String getShortestPangramEN(String s)
    {
        String result = s;

        int end = s.length();

        while (end != 0) {
            int begin = 0;

            while (begin != end) {
                String str = s.substring(begin++, end);

                if (str.length() >= 26 && isPangramEN(str) && str.length() < result.length())
                    result = str;
            }

            --end;
        }

        return result;
    }

    public static String getShortestPangramTR(String s)
    {
        String result = s;

        int end = s.length();

        while (end != 0) {
            int begin = 0;

            while (begin != end) {
                String str = s.substring(begin++, end);

                if (str.length() >= 29 && isPangramTR(str) && str.length() < result.length())
                    result = str;
            }

            --end;
        }

        return result;
    }

    public static boolean isIdentifier(String s)
    {
        if (s.isBlank() || s.equals("_"))
            return false;

        if (!Character.isJavaIdentifierStart(s.charAt(0)))
            return false;

        int len = s.length();

        for (int i = 1; i < len; ++i)
            if (!Character.isJavaIdentifierPart(s.charAt(i)))
                return false;

        return true;
    }

    public static boolean isPalindrome(String s)
    {
        int left = 0;
        int right = s.length() - 1;
        boolean cLeftSelected = false, cRightSelected = false;
        char cLeft = '\0', cRight;

        while (left < right) {
            if (!cLeftSelected) {
                cLeft = Character.toLowerCase(s.charAt(left));

                if (!Character.isLetter(cLeft)) {
                    ++left;
                    continue;
                }
                cLeftSelected = true;
            }

            if (!cRightSelected) {
                cRight = Character.toLowerCase(s.charAt(right));

                if (!Character.isLetter(cRight)) {
                    --right;
                    continue;
                }

                if (cLeft != cRight)
                    return false;

                cRightSelected = true;
            }

            ++left;
            --right;

            cLeftSelected = cRightSelected = false;
        }

        return true;
    }

    public static boolean isPangram(String s, String alphabet)
    {
        int len = alphabet.length();

        for (int i = 0; i < len; ++i)
            if (s.indexOf(alphabet.charAt(i)) == -1)
                return false;

        return true;
    }

    public static boolean isPangramEN(String s)
    {
        return isPangram(s.toLowerCase(), "abcdefghijklmnopqrstuwxvyz");
    }

    public static boolean isPangramTR(String s)
    {
        return isPangram(s.toLowerCase(), "abcçdefgğhıijklmnoöprsştuüvyz");
    }

    public static String join(String [] s, char delimiter)
    {
        return join(s, delimiter + "");
    }

    public static String join(String [] s, String delimiter)
    {
        String result = "";

        for (String str : s)
            result += str + delimiter;

        return result.substring(0, result.length() - delimiter.length());
    }

    public static String padLeading(String s, int len, char ch)
    {
        int length  = s.length();

        return len <= length ? s : repeat(len - length, ch) + s;
    }

    public static String padLeading(String s, int len)
    {
        return padLeading(s, len, ' ');
    }

    public static String padTrailing(String s, int len, char ch)
    {
        int length  = s.length();

        return len <= length ? s : s + repeat(len - length, ch);
    }

    public static String padTrailing(String s, int len)
    {
        return padTrailing(s, len, ' ');
    }

    public static String repeat(int count, char ch)
    {
        return String.format("%0" + count + "d", 0).replace('0', ch);
    }

    public static String reverse(String s)
    {
        char [] c = s.toCharArray();

        ArrayUtil.reverse(c);
        return String.valueOf(c);
    }

    public static String [] split(String str, String delimiters)
    {
        return split(str, delimiters, false);
    }

    public static String [] split(String str, String delimiters, boolean removeEmptyEntries)
    {
        String pattern = "[";

        int len = delimiters.length();

        for (int i = 0; i < len; ++i) {
            char c = delimiters.charAt(i);
            pattern += (c == '[' || c == ']') ? ("\\" + c) : c;
        }

        pattern += "]" + (removeEmptyEntries ? "+" : "");

        return str.split(pattern);
    }

    public static String squeeze(String s1, String s2)
    {
        String str = "";
        int len = s1.length();

        for (int i = 0; i < len; ++i) {
            char c = s1.charAt(i);

            if (!s2.contains(c + ""))
                str += c;
        }

        return str;
    }

    public static String trimLeading(String s)
    {
        int i;
        int len = s.length();

        for (i = 0; i < len && Character.isWhitespace(s.charAt(i)); ++i)
            ;

        return s.substring(i);
    }

    public static String trimTrailing(String s)
    {
        int i;

        for (i = s.length() - 1; i >= 0 && Character.isWhitespace(s.charAt(i)); --i)
            ;

        return s.substring(0, i + 1);
    }

    public static String wrapWith(String s, char prefix, char suffix)
    {
        return wrapWith(s, prefix + "", suffix + "");
    }

    public static String wrapWith(String s, String prefix, String suffix)
    {
        return String.format("%s%s%s", prefix, s, suffix);
    }

    public static String wrapWithStrip(String s, char prefix, char suffix)
    {
        return wrapWithStrip(s, prefix + "", suffix + "");
    }

    public static String wrapWithStrip(String s, String prefix, String suffix)
    {
        return wrapWith(s.strip(), prefix, suffix);
    }

    public static String wrapWithBracesStrip(String s)
    {
        return wrapWithStrip(s, '(', ')');
    }

    public static String wrapWithBraces(String s)
    {
        return wrapWith(s, '(', ')');
    }

    public static String wrapWithBCurlyracesStrip(String s)
    {
        return wrapWithStrip(s, '{', '}');
    }

    public static String wrapWithCurlyBraces(String s)
    {
        return wrapWith(s, '{', '}');
    }
}
