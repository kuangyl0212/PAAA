i=1
while i <=5:
    for a in range(5,0,-1):
        for b in range(a):
            print(' ',end='')
        for c in range(a,6):
            print(c,end='')
        i=i+1
        print('')
while i >=6:
    for h in range(2,6):
        for j in range(h-1):
            print(' ',end='')
        for k in range(h,6):
            print(k,end='')
        print('')
