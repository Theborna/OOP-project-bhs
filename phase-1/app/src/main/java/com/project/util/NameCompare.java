package com.project.util;

import java.util.Comparator;

import com.project.util.exception.changeViewException;

/**
 * this implementation of Comparator will compare two strings
 * in a much more profound way than the standard string comparator
 * which will help us find similar strings in different messages
 * and rank them based on similarity of the strings.
 * 
 * @return 0 for identical strings and positive if similar and negative for non
 *         similar strings
 **/
public class NameCompare implements Comparator<String> {

    public static final NameCompare INSTANCE = new NameCompare();

    /**
     * In information theory and computer science, the Damerau–Levenshtein distance
     * (named after Frederick J. Damerau and Vladimir I. Levenshtein[1][2][3]) is a
     * string metric for measuring the edit distance between two sequences.
     * Informally, the Damerau–Levenshtein distance between two words is the minimum
     * number of operations (consisting of insertions, deletions or substitutions of
     * a single character, or transposition of two adjacent characters) required to
     * change one word into the other.
     */
    @Override
    public int compare(String s, String t) {
        // Step 1
        var n = s.length();
        var m = t.length();
        if (n == 0)
            return m;
        if (m == 0)
            return n;
        int[][] d = new int[n + 1][m + 1];

        // Step 2
        for (var i = n; i >= 0; i--)
            d[i][0] = i;
        for (var j = m; j >= 0; j--)
            d[0][j] = j;

        // Step 3
        for (var i = 1; i <= n; i++) {
            var s_i = s.charAt(i - 1);
            // Step 4
            for (var j = 1; j <= m; j++) {

                // Check the jagged ld total so far
                if (i == j && d[i][j] > 4)
                    return n;

                var t_j = t.charAt(j - 1);
                var cost = (s_i == t_j) ? 0 : 1; // Step 5
                // Calculate the minimum
                var mi = d[i - 1][j] + 1;
                var b = d[i][j - 1] + 1;
                var c = d[i - 1][j - 1] + cost;

                if (b < mi)
                    mi = b;
                if (c < mi)
                    mi = c;

                d[i][j] = mi; // Step 6

                // Damerau transposition
                if (i > 1 && j > 1 && s_i == t.charAt(j - 2) && s.charAt(i - 2) == t_j) {
                    d[i][j] = Math.min(d[i][j], d[i - 2][j - 2] + cost);
                }
            }
        }

        // Step 7
        return d[n][m];
    }

    public static void main(String[] args) {// testing purposes
        while (true) {
            try {
                StdOut.prompt("enter first String");
                String s = StdIn.nextLine();
                StdOut.prompt("enter first String");
                String t = StdIn.nextLine();
                System.out.println(INSTANCE.compare(s, t));
            } catch (changeViewException e) {
                System.out.println("leaving not permitted");
            }
        }
    }
}
