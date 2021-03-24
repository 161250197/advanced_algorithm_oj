/**
 * @param {number[]} nums
 * @return {boolean}
 */
var find132pattern = function (nums) {
    const len = nums.length;
    const monotonicStack = [nums[len - 1]];
    let numk = undefined;
    for (let i = len - 2; i >= 0; i--)
    {
        const num = nums[i];
        if (num < numk)
        {
            return true;
        }
        while (monotonicStack[0] < num)
        {
            if (numk === undefined || numk < monotonicStack[0])
            {
                numk = monotonicStack[0];
            }
            monotonicStack.shift();
        }
        if (monotonicStack[0] !== num)
        {
            monotonicStack.unshift(num);
        }
    }
    return false;
};
