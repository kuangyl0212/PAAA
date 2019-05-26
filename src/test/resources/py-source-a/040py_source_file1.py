n=5
for i in range(1,n+1):
    for j in range(n-i+1,0,-1):
        a=(n-i)*" "
        b=j
        print(str(a),str(b),end='')
    print()
    
    
