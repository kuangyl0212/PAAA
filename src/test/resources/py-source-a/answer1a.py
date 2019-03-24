l='12345'
#输出前半部分
for i in range(len(l)-1):
    for k in range(len(l)-i-1):
        print(end=' ')
    print(l[len(l)-i-1:len(l)])
#输出后半部分
for i in range(len(l)):
    for k in range(i):
        print(end=' ')     
    print(l[i:len(l)])