
def calculate(head,leg):
    for i in range(0,head):
        
        for j in range(0,head):
            
            if i*2+j*4==leg and i+j==head:
                return i,j
print(calculate(12,32))



            
            

            
