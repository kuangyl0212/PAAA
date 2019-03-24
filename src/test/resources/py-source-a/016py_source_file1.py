for i in range(0,9):
    if 4-i>=0:
        print(" "*(4-i),end="")
    else:
        print(" "*(i-4),end="")
    for j in range(5-i,6):
        if 5-i>=0:
            print(j,end="")
        else:
            5-i=i-5
            print(j,end="")
    print("")
    
