def calculate(head, leg):
    i=0
    for chick in range(0,head+1):
        rab=head-chick
        t_leg=4*rab+2*chick
        if t_leg==leg and rab>=0:
            i=1
            print(chick,rab)
            break
    if i==0:
        print('无解')
        
head=12
leg=32
calculate(head,leg)