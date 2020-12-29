package dynamicProgramming;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * Throw n dices, the sum of the dices' faces is S. Given n, find the all possible value of S along with its probability.
 * @Example:
 * Input: n = 1
 * Output: [[1, 0.17], [2, 0.17], [3, 0.17], [4, 0.17], [5, 0.17], [6, 0.17]]
 * Explanation: Throw a dice, the sum of the numbers facing up may be 1, 2, 3, 4, 5, 6, and the probability of each result is 0.17.
 * @param: n an integer
  * @return a list of Map.Entry<sum, probability>
 */

/**
*   First find the result of a dice
 * Add another dice and continue to solve,
 * Add another dice and use the result of the previous dice to solve,
 **/
public class DicesSum {
    public static List<Map.Entry<Integer, Double>> dicesSum(int n) {
        List<Map.Entry<Integer,Double>> res = new ArrayList<>();
        double[][] dp = new double[n + 1][6 * n + 1];
        for (int i = 1; i < 7; i++) {
            dp[1][i] = 1.0 / 6.0;
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = i; j < 6 * n + 1; j++) {
                for (int k = 1; k < 7; k++) {
                    if (j > k){
                        dp[i][j] += dp[i - 1][j - k];
                    }
                }
                dp[i][j] /= 6.0;
            }
        }
        for (int i = n; i < 6 * n + 1 ; i++) {
            res.add(new AbstractMap.SimpleEntry<>(i,dp[n][i]));
        }
        return res;
    }

    public static void main(String[] args) {
        List<Map.Entry<Integer,Double>> res;
        res = dicesSum(10);
        for (Map.Entry<Integer,Double> entry : res){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
