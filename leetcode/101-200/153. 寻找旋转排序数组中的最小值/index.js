/**
 * @param {number[]} nums
 * @return {number}
 */
var findMin = function (nums) {
    const len = nums.length;
    let left = 0;
    let right = len - 1;
    while (left <= right)
    {
        const half = left + Math.floor((right - left) / 2);
        if (nums[half] > nums[right])
        {
            left = half + 1;
        } else if (nums[half] < nums[left])
        {
            right = half;
        } else
        {
            break;
        }
    }
    return nums[left];
};
