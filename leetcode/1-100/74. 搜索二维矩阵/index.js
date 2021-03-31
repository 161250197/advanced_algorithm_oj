/**
 * @param {number[][]} matrix
 * @param {number} target
 * @return {boolean}
 */
var searchMatrix = function (matrix, target) {
    const rowCount = matrix.length;
    const colCount = matrix[0].length;
    const row = (function () {
        let top = 0;
        let bottom = rowCount;
        while (top < bottom)
        {
            const row = top + Math.floor((bottom - top) / 2);
            if (matrix[row][0] > target)
            {
                bottom = row;
            } else
            {
                top = row + 1;
            }
        }
        return top - 1;
    }());
    if (row < 0)
    {
        return false;
    }
    const found = (function () {
        let left = 0;
        let right = colCount;
        while (left < right)
        {
            const col = left + Math.floor((right - left) / 2);
            if (matrix[row][col] > target)
            {
                right = col;
            } else if (matrix[row][col] < target)
            {
                left = col + 1;
            } else
            {
                return true;
            }
        }
        return false;
    }());
    return found;
};
