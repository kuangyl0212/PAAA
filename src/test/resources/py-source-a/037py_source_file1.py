for i in range(6)
    for j in range(0,6)
        if i>=j:
            print(" ",i,end=" ")
            i-=1
            for k in range(6,10)
                if i<=k:
                    print(" ",i,end=" ")
                    i+=1
end
