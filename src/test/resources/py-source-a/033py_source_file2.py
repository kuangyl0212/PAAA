def calculate(head,leg):
    if head>0 and leg>0 and head==int(head) and leg==int(leg):
        for ji in range(leg//2):
            for tu in range(leg//4):
                if ji*2+tu*4==leg and ji+tu==head:
                    print(str(ji)+str(tu))
    return ""
print(calculate(10,32))
print(calculate(12,32))
