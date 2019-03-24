"""
1.（5分）编写Python程序，使用循环语句在屏幕上显示如下图形。注：（1）不使用循环语句不得分；（2）第五行第一个数字之前不能有空格；（3）行与行之间不能有空行。(4)编程题中的过程代码要同时写在纸质试卷上。
 
"""
for i in range(0,5):
    for space in range(0,4-i):
        print(' ',end='')
    for j in range(5-i,6):
        print(j,end='')
    print()
for k in range(4,0,-1):
    for space in range(0,5-k):
         print(' ',end='')
    for z in range(6-k,6):
        print(z,end='')
    print()
        

