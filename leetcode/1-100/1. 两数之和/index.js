/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function (nums, target) {
    const numToIndexMap = Object.create(null);
    const len = nums.length;
    numToIndexMap[nums[0]] = 0;
    for (let i = 1; i < len; i++)
    {
        const num = nums[i];
        const anotherNum = target - num;
        if (numToIndexMap[anotherNum] !== undefined)
        {
            return [numToIndexMap[anotherNum], i];
        }
        numToIndexMap[num] = i;
    }
};
