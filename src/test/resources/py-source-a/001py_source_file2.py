﻿"""
2.（10分）在一个农场中，养了一群鸡和兔。一只鸡有两条腿，一只兔有四条腿。请编写程序，完成以下任务：
(1) 编写函数calculate(head, leg)，其中形参head和leg分别表示农场中所有动物的头和脚的个数。通过该函数计算，在屏幕上显示鸡和兔的个数。
例如：向该函数传入实参10和32，屏幕显示结果为：
4 6
（注：4和6中间没有空格；表示鸡和兔的个数分别为4和6）
(2) 假设所有动物的头和脚的个数分别为12和32，调用(1)中的函数，打印鸡和兔的个数。
注：（1）题中动物个数需为整数。（2）编程题中的过程代码要同时写在纸质试卷上。
"""
def calculate(head,leg):
head=int(input("head="))
leg=int(input("leg="))
    if m*2 + n*4= leg,m*n>=0
    chickhead=m,rabbithead=n
    print(chickhead,rabbithead)
    else:
        print("none")

head=12
leg=32
calculate(head,leg)
