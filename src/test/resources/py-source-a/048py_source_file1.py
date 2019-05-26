"""
1.（5分）编写Python程序，使用循环语句在屏幕上显示如下图形。注：（1）不使用循环语句不得分；（2）第五行第一个数字之前不能有空格；（3）行与行之间不能有空行。(4)编程题中的过程代码要同时写在纸质试卷上。
 
"""
#i=1
#for i in range(1,10):
    #n=5
for n in range(1,6,-1):
    print((n-1)*" ",end="")
    for j in range(1,n,-1):
        print(j,end="")
    print("")
for n in range(2,6):
    print((n-1)*" ",end="")
    for j in range(1,n):
        print(j,end="")
    print("")
