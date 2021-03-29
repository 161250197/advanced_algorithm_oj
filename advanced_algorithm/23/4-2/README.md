# 4-2 矩阵计算

## Description

Let's define a Series Whose recurrence formula is as follows :

C(n)= 3C(i-1) + 4C(i-2) + 5C(i-3) + 6C(i-4)

C(0)= 2

C(1)= 0

C(2)= 1

C(3)= 7

Now based on this Series a Matrix Mi,j of size nn is to be formed.The top left cell is(1,1) and the bottom right corner is (n,n). Each cell (i,j) of the Matrix contains either 1 or 0. If C( (i*j)^3 ) is odd, Mi,j is 1, otherwise, it's 0.Count the total number of ones in the Matrix.

## Input

First Line Of the input will contain an integer 'T'- the number of test cases . Each of the next 'T' lines consists of an integer 'n'.-denoting the size of the matrix.

Constraints :

1 ≤ T ≤ 1000

1 ≤ n ≤ 1000

## Output

For each test case, output a single Integer -the taste value fo the dish of size-n*n.

## Sample

### Sample Input 1

~~~
1
2
~~~

### Sample Output 1

~~~
0
~~~

## Trick & Solutions

1. isOdd 规律

    C(0) = 2，C(1) = 0，C(2) = 1，C(3) = 7

    isOdd(0) = false，isOdd(1) = false，isOdd(2) = true，isOdd(3) = true

    C(n)= 3C(i-1) + 4C(i-2) + 5C(i-3) + 6C(i-4)

    isOdd(n) = isOdd(i-1) ^ idOss(i-3)

    可以发现 idOdd 为 `false false true ture true false true` 的规律循环。

2. 计算折半

    计算矩阵的所有 (i*j)^3 可以只计算 `上半三角区域`

3. 注意下标

    下标是从 `1` 开始到 `n` 的
