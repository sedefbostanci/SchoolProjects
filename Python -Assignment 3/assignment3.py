import sys
hurap_file = open(sys.argv[1], "r")
schuckscii_file = open(sys.argv[2], "r")
virus_codes_file = open(sys.argv[3], "r")

hurap_list=[]
new=[]
differentchar=[]
for s in hurap_file:
    for i in s:
        hurap_list.append(i)
    for m in hurap_list:
        if m == "\n":
            a=hurap_list.index(m)
    if a != 8:
        hurap_list.pop(a)
    else:
        hurap_list.pop()
    if hurap_list[0] == "0" :
        fourtime=[hurap_list[x:x+4] for x in range(0,len(hurap_list),4)]
        for k in range(len(fourtime)):
            for n in range(len(fourtime[k])):
                if n == 0:
                    a = int(fourtime[int(k)][int(n)])*8
                elif n==1:
                    b= int(fourtime[int(k)][int(n)])*4
                elif n==2:
                    c= int(fourtime[int(k)][int(n)])*2
                elif n==3:
                    d= int(fourtime[int(k)][int(n)])
            toplam= int(a)+int(b)+int(c)+int(d)
            if toplam== 10:
                toplam= "A"
            elif toplam==11:
                toplam= "B"
            elif toplam==12:
                toplam= "C"
            elif toplam==13:
                toplam= "D"
            elif toplam==14:
                toplam= "E"
            elif toplam==15:
                toplam= "F"
            fourtime[k]= (toplam)
        for s in fourtime:
            new.append(s)
        new.append("\n")
        hurap_list=[]
    elif hurap_list[0] == "1":
        fourtime=[hurap_list[x:x+4] for x in range(0,len(hurap_list),4)]
        for k in range(len(fourtime)):
            for n in range(len(fourtime[k])):
                if n == 0:
                    a = int(fourtime[int(k)][int(n)])*8
                elif n==1:
                    b= int(fourtime[int(k)][int(n)])*4
                elif n==2:
                    c= int(fourtime[int(k)][int(n)])*2
                elif n==3:
                    d= int(fourtime[int(k)][int(n)])
            toplam= int(a)+int(b)+int(c)+int(d)
            if toplam== 10:
                toplam= "A"
            elif toplam==11:
                toplam= "B"
            elif toplam==12:
                toplam= "C"
            elif toplam==13:
                toplam= "D"
            elif toplam==14:
                toplam= "E"
            elif toplam==15:
                toplam= "F"
            fourtime[k]= (toplam)
        for s in fourtime:
            new.append(s)
        new.append("\n")
        hurap_list=[]

    else:
        for s in hurap_list:
            differentchar.append(s)
        hurap_list=[]

print("""*********************
     Mission 00 
*********************""", end="\n\n")
print("""--- hex of encrypted code ---
-----------------------------""", end="\n\n")
for i in new:
    print(i,end="")

x=[]
yeni=[]
for i in schuckscii_file:
    for s in i:
        x.append(s)
    del x[4:]
    yeni.append(x)
    x=[]

sedef=[]
el=[]
sayaç=0

for m in new :
    if m == "\n":
        sayaç+= 1
        a=new.index(m)

for s in range(int(sayaç)):
    for m in new:
        if m == "\n":
            sayaç+= 1
            a=new.index(m)
    for m in new[0:int(a)]:
        sedef.append(m)
    two=[sedef[x:x+2] for x in range(0,len(sedef),2)]
    for m in range(len(two)):
        for n in range(len(yeni)):
            if str(two[int(m)][0])== yeni[int(n)][2] :
                if str(two[int(m)][1])==yeni[int(n)][3]:
                    two[int(m)]=yeni[int(n)][0]
                    break
    for s in two:
        el.append(s)
    el.append("\n")
    sedef=[]
    del new[0:(int(a)+ 1)]

differentchar.pop(0)
toplam=[]
if differentchar[0] == "0":
    differentchar.reverse()
    for i in range(len(differentchar)):
        s=pow(2,int(i))
        a=int(s)*(int(differentchar[int(i)]))
        toplam.append(int(a))

    s=0
    for i in toplam:
        s+=int(i)
    key=int(s)
elif differentchar[0]== "1":
    for i in range(len(differentchar)):
        if differentchar[i]=="0":
            differentchar[i]="1"
        elif differentchar[i]=="1":
            differentchar[i]="0"
    differentchar.reverse()
    if differentchar[0]=="0":
        differentchar[0]="1"
    elif differentchar[0]=="1":
        differentchar[0]="0"
        for i in differentchar[1:]:
            if i == "1":
                i="0"
            else:
                break
    for i in range(len(differentchar)):
        s=pow(2,int(i))
        a=int(s)*(int(differentchar[int(i)]))
        toplam.append(int(a))

    s=0
    for i in toplam:
        s+=int(i)
    key=int(s)*int(-1)

mike=[]
for i in el:
    if i!="\n":
        for s in range(len(yeni)):
            if yeni[int(s)][0]== i:
                original=((int(s)-key))%(len(yeni))
        a=yeni[int(original)][0]
        mike.append(a)
    else:
        mike.append("\n")

print("""\n--- encrypted code ----
-----------------------""", end="\n\n")
for i in el:
    print(i,end="")

print("""\n--- decrypted code ---
----------------------""", end="\n\n")
for i in mike:
    print(i,end="")

dict={}
m=[]
for line in virus_codes_file.read().splitlines():
    a=line.split(":")
    m.append(a)
for i in range(len(m)):
    dict[m[i][0]]=m[i][1]

list_2=[]
dust=[]
for m in mike:
    if m != '\n':
        list_2.append(m)
    elif m=="\n":
        dust.append(list_2)
        list_2=[]

iki=[]
üç=[]
for L in dust:
    f=''
    for i in range(0,len(L)):
        f=f+L[i]
    iki.append(f)
    üç.append(iki)
    iki=[]

hey=[]
a=[]

for i in üç:
    for m in i:
        for c in dict.keys():
            while c in m:
                a=m.replace(c,dict.get(c))
                m = a
                break
        if c not in dict.keys():
            hey.append(a)
        else:
            hey.append(m)


print("""\n*********************
     Mission 01 
*********************""", end="\n\n")

for i in hey:
    print(i,end="\n")

yo=[]

for i in hey:
    for s in i:
        yo.append(s)
    yo.append("\n")


pişt=[]
for i in yo:
    if i!="\n":
        for s in range(len(yeni)):
            if yeni[int(s)][0]== i:
                original=((int(s)+key))%(len(yeni))
        a=yeni[int(original)][0]
        pişt.append(a)
    else:
        pişt.append("\n")


print("""\n*********************
     Mission 10 
*********************""", end="\n\n")

print("""--- encrypted code ---
----------------------""", end="\n\n")

for i in pişt:
    print(i,end="")


a=open(sys.argv[2],"r")

dict2={}
n=[]
for line in a.read().splitlines():
    c=line.split("\t")
    n.append(c)
for i in range(len(n)):
    dict2[n[int(i)][0]]=n[int(i)][1]


değişti=[]
for i in pişt:
    if i!= "\n":
        for c in dict2.keys():
            if c ==str(i):
                a=i.replace(c,dict2.get(c))
                değişti.append(a)
    elif i=="\n":
        değişti.append(i)

print("""\n--- hex of encrypted code ---
-----------------------------""", end="\n\n")

for i in değişti:
    print(i,end="")


hexlist=[]
for i in değişti:
    for m in i:
        hexlist.append(m)

nance=[]
steve=[]
for i in hexlist:
    if i != "\n":
        nance.append(i)
    elif i == "\n":
        steve.append(nance)
        nance=[]

for i in steve:
    for s in range(len(i)):
        if i[s] == "0":
            i[s]="0000"
        elif i[s] == "1":
            i[s]="0001"
        elif i[s] == "2":
            i[s]="0010"
        elif i[s] == "3":
            i[s]="0011"
        elif i[s] == "4":
            i[s]="0100"
        elif i[s] == "5":
            i[s]="0101"
        elif i[s] == "6":
            i[s]="0110"
        elif i[s] == "7":
            i[s]="0111"
        elif i[s] == "8":
            i[s]="1000"
        elif i[s] == "9":
            i[s]="1001"
        elif i[s] == "A":
            i[s]="1010"
        elif i[s] == "B":
            i[s]="1011"
        elif i[s] == "C":
            i[s]="1100"
        elif i[s] == "D":
            i[s]="1101"
        elif i[s] == "E":
            i[s]="1110"
        elif i[s] == "F":
            i[s]="1111"

for i in steve:
    i.append("\n")

lucas=[]
for i in steve:
    for s in i:
        lucas.append(s)


print("""\n--- bin of encrypted code ---
-----------------------------""", end="\n\n")

for i in lucas:
    print(i,end="")

hurap_file.close()
schuckscii_file.close()
virus_codes_file.close()
