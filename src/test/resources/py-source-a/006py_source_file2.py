def calculate(head,leg):
    for i in range(head+1):
        for j in range(head+1):
            k=2*i+4*j
            t=i+j
            if k==leg and t==head:
                print(i,j)

calculate(12,32)
        
