/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxLength = function (nums) {
    const map = new Map();
    let counter = 0;
    let maxLength = 0;
    map.set(counter, -1);
    const numCount = nums.length;
    for (let i = 0; i < numCount; i++)
    {
        if (nums[i] === 0)
        {
            counter--;
        } else
        {
            counter++;
        }
        if (map.has(counter))
        {
            maxLength = Math.max(maxLength, i - map.get(counter));
        } else
        {
            map.set(counter, i);
        }
    }
    return maxLength;
};
