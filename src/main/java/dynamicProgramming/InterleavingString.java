package dynamicProgramming;

/**
 * @Descrption: Given three strings: s1, s2, s3, determine whether s3 is formed by the interleaving of s1 and s2.
 */
public class InterleavingString {
    /**
     * @param s1: A string
     * @param s2: A string
     * @param s3: A string
     * @return: Determine whether s3 is formed by interleaving of s1 and s2
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()){
            return false;
        }
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < s1.length() + 1; i++){
            if (s1.charAt(i - 1) == s3.charAt(i -1) && dp[i - 1][0]){
                dp[i][0] = true;
            }
        }

        for (int i = 1; i < s2.length() + 1; i++) {
            if (s2.charAt(i - 1) == s3.charAt(i - 1) && dp[0][i - 1]){
                dp[0][i] = true;
            }
        }

        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if ((s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j]) || (s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

}
