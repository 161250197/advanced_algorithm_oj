/**
 * @param {number[]} nums
 * @param {number} target
 * @return {boolean}
 */
var search = function (nums, target) {
    // 检查特判情况
    if (nums[0] === target)
    {
        return true;
    }

    // 边查找旋转位置 k 边查找目标数字
    let k = 1;
    const len = nums.length;
    while (k < len)
    {
        const num = nums[k];
        if (num === target)
        {
            return true;
        }
        if (nums[k - 1] > nums[k % len])
        {
            break;
        }
        k++;
    }
    if (k === len)
    {
        return false;
    }

    // 检查数组下界
    let leftIndex = k + 1;
    let leftNum = nums[leftIndex];
    if (leftNum === target)
    {
        return true;
    }
    if (leftNum > target)
    {
        return false;
    }

    // 检查数组上界
    let rightIndex = len - 1;
    let rightNum = nums[rightIndex];
    if (rightNum === target)
    {
        return true;
    }
    if (rightNum < target)
    {
        return false;
    }

    // 二分查找目标值
    while (leftIndex <= rightIndex)
    {
        const halfIndex = leftIndex + Math.floor((rightIndex - leftIndex) / 2);
        const halfNum = nums[halfIndex];
        if (halfNum === target)
        {
            return true;
        }
        if (halfNum > target)
        {
            rightIndex = halfIndex - 1;
        } else
        {
            leftIndex = halfIndex + 1;
        }
    }
    return false;
};
