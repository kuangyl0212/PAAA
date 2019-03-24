"""
2.（10分）在一个农场中，养了一群鸡和兔。一只鸡有两条腿，一只兔有四条腿。请编写程序，完成以下任务：
(1) 编写函数calculate(head, leg)，其中形参head和leg分别表示农场中所有动物的头和脚的个数。通过该函数计算，在屏幕上显示鸡和兔的个数。
例如：向该函数传入实参10和32，屏幕显示结果为：
4 6
（注：4和6中间没有空格；表示鸡和兔的个数分别为4和6）
(2) 假设所有动物的头和脚的个数分别为12和32，调用(1)中的函数，打印鸡和兔的个数。
注：（1）题中动物个数需为整数。（2）编程题中的过程代码要同时写在纸质试卷上。
"""
def calculate(head,leg):
    for chickenhead in range(1,head):
        rabbithead=head-chickenhead
        chickenleg=chickenhead*2
        rabbitleg=rabbithead*4
        if leg==chickenleg+rabbitleg:
           print(chickenhead,rabbithead)
    return("")
print(calculate(12,32))
