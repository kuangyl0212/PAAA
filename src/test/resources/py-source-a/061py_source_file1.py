﻿"""
1.（5分）编写Python程序，使用循环语句在屏幕上显示如下图形。注：（1）不使用循环语句不得分；（2）第五行第一个数字之前不能有空格；（3）行与行之间不能有空行。(4)编程题中的过程代码要同时写在纸质试卷上。
 
"""
n=5
while 0<n<=5:
    i=n
    s=''
    while 0<i<=5:
        s+=str(i)
        i+=1
    print('{:>5}'.format(s).end='')
    print('')
    n-=1