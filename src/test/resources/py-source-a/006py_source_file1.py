for i in range(1,6):
    for k in range(1,6-i):
        print(' ',end='')
    for j in range(6-i,6):
        print(j,end='')
    print()
for i in range(2,6):
    for k in range(i-1):
        print(' ',end='')
    for j in range(i,6):
        print(j,end='')
    print()
