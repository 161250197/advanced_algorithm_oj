/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function (nums) {
    let lastNum = nums[nums.length - 1];
    let numCount = 1;
    let index = nums.length - 2;
    while (index >= 0)
    {
        const num = nums[index];
        if (num === lastNum)
        {
            if (numCount === 2)
            {
                nums.splice(index, 1);
            } else
            {
                numCount++;
            }
        } else
        {
            lastNum = num;
            numCount = 1;
        }
        index--;
    }
    return nums.length;
};
