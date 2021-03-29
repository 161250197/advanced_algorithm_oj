/**
 * @param {number} n - a positive integer
 * @return {number} - a positive integer
 */
var reverseBits = function (n) {
    const bitStr = n.toString(2);
    const preZeroCount = 32 - bitStr.length;
    const bitArr = [...bitStr];
    const reverseArr = bitArr.reverse().concat(Array(preZeroCount).fill(0));
    const reverseBitStr = reverseArr.join('');
    const reverseBitNum = Number.parseInt(reverseBitStr, 2);
    return reverseBitNum;
};
