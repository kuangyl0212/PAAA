"""
1.（5分）编写Python程序，使用循环语句在屏幕上显示如下图形。注：（1）不使用循环语句不得分；（2）第五行第一个数字之前不能有空格；（3）行与行之间不能有空行。(4)编程题中的过程代码要同时写在纸质试卷上。
 
"""
for m in range(5,1,-1):
    print((m-1)*" ",end="")
    for n in range(m,6):
        print(n,end="")
    print()
i=5
for i in range(1,6):
    print((i-1)*" ",end="")
    for j in range(i,6):
        print(j,end="")
    print()
i=i-1
if i<0:
    end
