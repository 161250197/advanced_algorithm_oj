/**
 * @param {number} n
 * @param {number} minProfit
 * @param {number[]} group
 * @param {number[]} profit
 * @return {number}
 */
var profitableSchemes = function (n, minProfit, group, profit) {
    const moder = Math.pow(10, 9) + 7;
    const groupCount = group.length;
    const dp = Array(groupCount + 1);
    for (let toGroupIndex = groupCount; toGroupIndex >= 0; toGroupIndex--)
    {
        dp[toGroupIndex] = Array(n + 1);
        for (let canUseN = n; canUseN >= 0; canUseN--)
        {
            dp[toGroupIndex][canUseN] = Array(minProfit + 1).fill(0);
        }
    }
    for (let canUseN = 0; canUseN <= n; canUseN++)
    {
        dp[0][canUseN][0] = 1;
    }
    for (let i = 0; i < groupCount; i++)
    {
        const groupNValue = group[i];
        const profitValue = profit[i];
        const toGroupIndex = i + 1;
        for (let canUseN = 0; canUseN <= n; canUseN++)
        {
            for (let targetMinProfit = 0; targetMinProfit <= minProfit; targetMinProfit++)
            {
                dp[toGroupIndex][canUseN][targetMinProfit] = dp[toGroupIndex - 1][canUseN][targetMinProfit];
                if (canUseN >= groupNValue)
                {
                    let usedNewTargetProfitValue = targetMinProfit - profitValue;
                    if (usedNewTargetProfitValue < 0)
                    {
                        usedNewTargetProfitValue = 0;
                    }
                    dp[toGroupIndex][canUseN][targetMinProfit] += dp[toGroupIndex - 1][canUseN - groupNValue][usedNewTargetProfitValue];
                }
                if (dp[toGroupIndex][canUseN][targetMinProfit] >= moder)
                {
                    dp[toGroupIndex][canUseN][targetMinProfit] -= moder;
                }
            }
        }
    }
    return dp[groupCount][n][minProfit];
};
