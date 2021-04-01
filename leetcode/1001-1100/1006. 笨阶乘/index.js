/**
 * @param {number} N
 * @return {number}
 */
var clumsy = function (N) {
    let result = 0;
    let n = N;
    let partResult = n;
    while (n > 1)
    {
        n--;
        if (n > 0)
        {
            partResult = partResult * n;
        } else
        {
            break;
        }
        n--;
        if (n > 0)
        {
            partResult = partResult < 0 ? Math.ceil(partResult / n) : Math.floor(partResult / n);
        } else
        {
            break;
        }
        n--;
        if (n > 0)
        {
            result = result + partResult;
            partResult = n;
        } else
        {
            break;
        }
        n--;
        if (n > 0)
        {
            result = result + partResult;
            partResult = -n;
        } else
        {
            break;
        }
    }
    result = result + partResult;
    return result;
};
