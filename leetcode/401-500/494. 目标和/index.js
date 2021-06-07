/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var findTargetSumWays = function (nums, target) {
    const numCount = nums.length;
    let targetNums = [target];
    let targetNumCountMap = {};
    targetNumCountMap[target] = 1;
    for (let i = 0; i < numCount; i++)
    {
        let newTargetNums = [];
        let newTargetNumCountMap = {};
        const num = nums[i];
        for (let targetNum of targetNums)
        {
            let newTargetNumCount = targetNumCountMap[targetNum];
            let newTargetNum = targetNum + num;
            if (newTargetNumCountMap[newTargetNum] === undefined)
            {
                newTargetNumCountMap[newTargetNum] = 0;
                newTargetNums.push(newTargetNum);
            }
            newTargetNumCountMap[newTargetNum] += newTargetNumCount;

            newTargetNum = targetNum - num;
            if (newTargetNumCountMap[newTargetNum] === undefined)
            {
                newTargetNumCountMap[newTargetNum] = 0;
                newTargetNums.push(newTargetNum);
            }
            newTargetNumCountMap[newTargetNum] += newTargetNumCount;
        }
        targetNums = newTargetNums;
        targetNumCountMap = newTargetNumCountMap;
    }
    return targetNumCountMap[0] || 0;
};
