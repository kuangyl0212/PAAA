def calculate(head,leg):
    for a in range(0,1000000):
        for b in range(0,1000000):
            if a+b==head and 2*a+4*b==leg:
                print(a,b)
print(calculate(12,32))




    
    
