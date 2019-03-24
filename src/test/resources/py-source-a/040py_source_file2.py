#(1)
def calculate(head,leg):
    for chicken_head in range(1,head+1):
        rabbit_head=head-chicken_head
        total_leg=2*chicken_head+4*rabbit_head
        if total_leg==leg:
            return chicken_head,rabbit_head
#(2)
print(calculate(12,32))
        
