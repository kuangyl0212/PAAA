def calculate(head,leg):
    for n_chicken in range(0,head+1):
        n_rabbit=head-n_chicken
        total_leg=2*n_chicken+4*n_rabbit
        if total_leg==leg:
            return print(n_chicken," ",n_rabbit)

def solve():
    heads=int(input("heads="))
    legs=int(input("legs="))
    calculate(heads,legs)
        
       





    
