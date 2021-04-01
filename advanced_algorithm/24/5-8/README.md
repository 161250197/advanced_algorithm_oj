# 5-8 先升后降

## Description

从一列不重复的数中筛除尽可能少的数使得从左往右看，这些数是从小到大再从大到小的。

## Input

输入第一行为用例个数， 每个测试用例输入是一个数组，数值通过空格隔开。

## Output

输出筛选之后的数组，用空格隔开。如果有多种结果，则一行一种结果， 单个输入的所有结果按从小到大排序，排序的key的优先级随index递增而递减 例如 3 4 7 6； 1 3 7 5； 1 2 7 6； 1 3 7 6 排序成 1 2 7 6；1 3 7 5；1 3 7 6； 3 4 7 6；

## Sample

### Sample Input 1

~~~
4
1 2 4 7 11 10 9 15 3 5 8 6
1 3 5 4 7 6 4 5 3
1 2 3
3 2 1
~~~

### Sample Output 1

~~~
1 2 4 7 11 10 9 8 6
1 3 4 7 6 4 3
1 3 4 7 6 5 3
1 3 5 7 6 4 3
1 3 5 7 6 5 3
1 2 3
3 2 1
~~~