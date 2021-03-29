# 4-7 数组查询

## Description

Given an array, the task is to complete the function which finds the maximum sum subarray, where you may remove at most one element to get the maximum sum.

## Input

第一行为测试用例个数T；后面每两行表示一个用例，第一行为用例中数组长度N，第二行为数组具体内容。

## Output

每一行表示对应用例的结果。

## Sample

### Sample Input 1

~~~
1
5
1 2 3 -4 5
~~~

### Sample Output 1

~~~
11
~~~

## Trick & Solutions

注意：`空集` 不符合题目要求！

遍历查找所有负数的下标。

* 如果没有找到，直接返回数组和；
* 对每个下标进行移除，使用贪心查找此时的最大数组和。
