﻿"""
1.（5分）编写Python程序，使用循环语句在屏幕上显示如下图形。注：（1）不使用循环语句不得分；（2）第五行第一个数字之前不能有空格；（3）行与行之间不能有空行。(4)编程题中的过程代码要同时写在纸质试卷上。
 
"""
for i in range(5,0,-1):
    for j in range(1,6):
        if i<=j:
            print(j,end='')
        else:
            print(' ',end='')
    print()
for k in range(2,6):
    for l in range(1,6):
        if k<=l:
            print(l,end='')
        else:
            print(' ',end='')
    print()
 
