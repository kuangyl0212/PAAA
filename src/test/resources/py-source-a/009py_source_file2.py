def calculate(head,leg):
    int(head)=int(r_head)+int(c_head)
    int(leg)=4*int(r_leg)+2*int(c_leg)
    int(r_leg)=4*int(r_head)
    int(c_leg)=2*int(c_head)
    
    return c_head,r_head
print(calculate(10,32))
