# 3-7 和最大的连续降序字符

## Description

Archana is very fond of strings. She likes to solve many questions related to strings. She comes across a problem which she is unable to solve. Help her to solve. The problem is as follows: Given is a string of length L. Her task is to find the longest string from the given string with characters arranged in descending order of their ASCII code and in arithmetic progression. She wants the common difference should be as low as possible(at least 1) and the characters of the string to be of higher ASCII value.

## Input

The first line of input contains an integer T denoting the number of test cases. Each test contains a string s of lengthL.

1<= T <= 100

3<= L <=1000

A<=s[i]<=Z

The string contains minimum three different characters.

## Output

For each test case print the longest string.Case 1:Two strings of maximum length are possible- “CBA” and “RPQ”. But he wants the string to be of higher ASCII value therefore, the output is “RPQ”.Case 2:The String of maximum length is “JGDA”.

## Sample

### Sample Input 1

~~~
2
ABCPQR
ADGJPRT
~~~

### Sample Output 1

~~~
RQP
JGDA
~~~

## Trick & Solutions

1. 题目的意思是，从给定的字符串中 `随意` 选择若干字符，组成降序的等差数列，同时保证以下条件：

    1. 数列尽可能长；

    2. 相同长度的数列，优先选择等差小的数列；

    3. 长度、等差都相同的数列，优先选择字典序大的。
