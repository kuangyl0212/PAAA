for i in range(1,10):
    if i<=5:
        print(" "*(5-i),end="")
        for j in range(6-i,6):
            print(j,end="")
    else:
        print(" "*(i-5),end="")
        for j in range(i-4,6):
            print(j,end="")
    print("")
    
