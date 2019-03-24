for i in range(1,6):
    for j in range(6-i,6):
        if 6-i<j:
            print(' ',end='')
        else:
            print(j,end='')
    print('')
