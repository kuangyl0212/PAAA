﻿"""
1.（5分）编写Python程序，使用循环语句在屏幕上显示如下图形。注：（1）不使用循环语句不得分；（2）第五行第一个数字之前不能有空格；（3）行与行之间不能有空行。(4)编程题中的过程代码要同时写在纸质试卷上。
 
"""
for i in range(0,5):
    a=5
    b=4-i
    n=0
    while a>b:
        if n==0:
	    print(' '*b,a,end='')
	    a=a-1
	    n=n+1
	else:
            print(a,end='')
            a=a-1
    print('')

for j in range(0,5):
    b=j
    
    