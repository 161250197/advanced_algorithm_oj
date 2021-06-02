/**
 * @param {number[]} nums
 * @param {number} k
 * @return {boolean}
 */
var checkSubarraySum = function (nums, k) {
    var numCount = nums.length;
    if (numCount < 2)
    {
        return false;
    }
    var doubleZero = (function () {
        for (var i = 1; i < numCount; i++)
        {
            if (nums[i] === 0 && nums[i - 1] === 0)
            {
                return true;
            }
        }
        return false;
    }());
    if (doubleZero)
    {
        return true;
    }
    var maxSum = (function () {
        var sum = 0;
        for (var num of nums)
        {
            sum += num;
        }
        return sum;
    }());
    if (maxSum < k)
    {
        return false;
    }
    if (maxSum % k === 0)
    {
        return true;
    }

    var sums = [nums[0] % k];
    for (var numIndex = 1; numIndex < numCount; numIndex++)
    {
        var num = nums[numIndex] % k;
        if (num !== 0)
        {
            for (var i = 0; i < numIndex; i++)
            {
                sums[i] += num;
                if (sums[i] >= k)
                {
                    sums[i] -= k;
                    if (sums[i] === 0)
                    {
                        return true;
                    }
                }
            }
        }
        sums[numIndex] = num;
    }
    return false;
};
