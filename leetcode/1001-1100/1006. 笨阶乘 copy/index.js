/**
 * @param {number[]} stones
 * @return {number}
 */
var lastStoneWeightII = function (stones) {
    const stoneCount = stones.length;
    const stoneWeightSum = stones.reduce((s1, s2) => s1 + s2);
    const maxReduceWeight = Math.floor(stoneWeightSum / 2);
    const validReduceWeightMatrix = Array(stoneCount + 1);
    for (let i = stoneCount; i >= 0; i--)
    {
        validReduceWeightMatrix[i] = Array(maxReduceWeight + 1).fill(false);
        validReduceWeightMatrix[i][0] = true;
    }
    for (let stoneIndex = 0; stoneIndex < stoneCount; stoneIndex++)
    {
        const stone = stones[stoneIndex];
        const rowIndex = stoneIndex + 1;
        for (let targetReduceWeight = 1; targetReduceWeight <= maxReduceWeight; targetReduceWeight++)
        {
            validReduceWeightMatrix[rowIndex][targetReduceWeight] = (
                validReduceWeightMatrix[rowIndex - 1][targetReduceWeight] ||
                stone === targetReduceWeight ||
                (stone < targetReduceWeight && validReduceWeightMatrix[rowIndex - 1][targetReduceWeight - stone])
            );
        }
    }
    for (let reduceWeight = maxReduceWeight; reduceWeight >= 0; reduceWeight--)
    {
        if (validReduceWeightMatrix[stoneCount][reduceWeight])
        {
            return stoneWeightSum - reduceWeight * 2;
        }
    }
};
