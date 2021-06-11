/**
 * @param {number} n
 * @return {number}
 */
var numSquares = function (n) {
    const dp = [0];
    for (let i = 1; i <= n; i++)
    {
        let min = i;
        let maxSquareRoot = Math.floor(Math.sqrt(i));
        for (let j = maxSquareRoot; j > 1; j--)
        {
            min = Math.min(min, 1 + dp[i - j * j]);
        }
        dp[i] = min;
    }
    return dp[n];
};
