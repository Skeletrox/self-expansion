from random import randint
	
string = input("Enter string to encrypt: ")
lth = len(string)
string2 =[]
offset = randint (0,9)
offset = int(offset)
string2.append (chr(offset+48))
for i in range (0,lth):
	tp = ord(string[i])
	tp += offset
	tp += i
	string2.append (chr(tp))
print ("Encrypted string is ", end=''),
for i in range (0, lth+1):
	print (string2[i], end='')
print ("")
