"""
1.（5分）编写Python程序，使用循环语句在屏幕上显示如下图形。注：（1）不使用循环语句不得分；（2）第五行第一个数字之前不能有空格；（3）行与行之间不能有空行。(4)编程题中的过程代码要同时写在纸质试卷上。
 
"""
for i in range(0,5):
    a=4-i
    print(" "*a,end='')
    b=5-i
    while b<6:
        print(b,end='')
        b+=1
    print("")
for i in range(1,5):
    print(" "*i,end='')
    c=i+1
    while c<6:
        print(c,end='')
        c+=1
    print('')
