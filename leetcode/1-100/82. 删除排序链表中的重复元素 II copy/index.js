/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var subsetsWithDup = function (nums) {
    const numToCountMap = Object.create(null);
    const numArr = [];
    for (const num of nums)
    {
        let count = 1;
        if (numToCountMap[num] === undefined)
        {
            numArr.push(num);
        } else
        {
            count = count + numToCountMap[num];
        }
        numToCountMap[num] = count;
    }
    let result = [[]];
    for (const num of numArr)
    {
        const numCount = numToCountMap[num];
        let newArr = [];
        let lastArr = result;
        for (let i = 0; i < numCount; i++)
        {
            lastArr = lastArr.map(arr => [...arr, num]);
            newArr = newArr.concat(lastArr);
        }
        result = result.concat(newArr);
    }
    return result;
};
