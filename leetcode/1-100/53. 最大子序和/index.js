/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    let sum = 0;
    let max = -Number.MAX_SAFE_INTEGER;
    for (let num of nums)
    {
        sum = Math.max(num, sum + num);
        max = Math.max(sum, max);
    }
    return max;
};
