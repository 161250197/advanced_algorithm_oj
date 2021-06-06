/**
 * @param {string[]} strs
 * @param {number} m
 * @param {number} n
 * @return {number}
 */
var findMaxForm = function (strs, m, n) {
    function getOnesZeros (str) {
        let oneCount = 0;
        let zeroCount = 0;
        for (let c of str)
        {
            if (c === '0')
            {
                zeroCount++;
            } else
            {
                oneCount++;
            }
        }
        return {
            oneCount,
            zeroCount
        };
    }
    const strCount = strs.length;
    let dp = Array(strCount + 1);
    for (let i = 0; i <= strCount; i++)
    {
        dp[i] = Array(m + 1);
        for (let j = 0; j <= m; j++)
        {
            dp[i][j] = Array(n + 1).fill(0);
        }
    }
    for (let i = 1; i <= strCount; i++)
    {
        const str = strs[i - 1];
        const { oneCount, zeroCount } = getOnesZeros(str);
        for (let j = 0; j <= m; j++)
        {
            for (let k = 0; k <= n; k++)
            {
                dp[i][j][k] = dp[i - 1][j][k];
                if (j >= zeroCount && k >= oneCount)
                {
                    dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeroCount][k - oneCount] + 1);
                }
            }
        }
    }
    return dp[strCount][m][n];
};
