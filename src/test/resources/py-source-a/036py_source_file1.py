"""
1.（5分）编写Python程序，使用循环语句在屏幕上显示如下图形。注：（1）不使用循环语句不得分；（2）第五行第一个数字之前不能有空格；（3）行与行之间不能有空行。(4)编程题中的过程代码要同时写在纸质试卷上。
 
"""
n=6
def first():
    for i in range(1,6):
        for j in range(1,n-i):
            print(end=" ")
        for k in range(n-i,n):
            print(k,end="")
        print("")

def second():
    for i in range(2,6):
        for j in range(1,i):
            print(end=" ")
        for k in range(i,6):
            print(k,end="")
        print("")

first()
second()
