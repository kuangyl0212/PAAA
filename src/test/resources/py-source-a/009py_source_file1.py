#"""
#1.（5分）编写Python程序，使用循环语句在屏幕上显示如下图形。注：（1）不使用循环语句不得分；（2）第五行第一个数字之前不能有空格；（3）行与行之间不能有空行。(4)编程题中的过程代码要同时写在纸质试卷上。
m=5
for i in range(4,-1,-1):
    print(i*'  ',end=' ')
    for j in range(m,6):
        print(j,end=' ')
    m=m-1
    print()
q=2
for k in range(1,5):
    print(k*'  ',end=' ')
    for l in range(q,6):
        print(l,end=' ')
    q=q+1
    print()
    
