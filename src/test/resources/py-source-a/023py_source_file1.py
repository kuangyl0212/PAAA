"""
1.（5分）编写Python程序，使用循环语句在屏幕上显示如下图形。注：（1）不使用循环语句不得分；（2）第五行第一个数字之前不能有空格；（3）行与行之间不能有空行。(4)编程题中的过程代码要同时写在纸质试卷上。
 
"""
for i in range(1,6):
    for j in range(5-i):
        print(' ',end='')
    if i==1:
        print('5')
    if i==2:
        print('45')
    if i==3:
        print('345')
    if i==4:
        print('2345')
    if i==5:
        print('12345')
for i in range(1,6):
    for j in range(i):
        print(' ',end='')
    if i==1:
        print('2345')
    if i==2:
        print('345')
    if i==3:
        print('45')
    if i==4:
        print('5')

        
