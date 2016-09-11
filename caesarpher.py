#!usr/bin/env python3
'''
This program runs in Python 3.x. Any users of Python 2.x must upgrade before using this.
This uses the Mersenne Twister random number generator, which is completely deterministic and thus unsuitable for cryptography.
As this program is just an example, I'm really not hitting industry standards yet.
'''
from random import randint 		#Imports randint()

string = input("Enter string to encrypt: ")
lth = len(string)
string2 =[]
offset = randint (1,9)			#Trying to extend range to double digits causes a bug, making decryption a pain.
string2.append (chr(offset+48))		#Hmmmm, what does this do?
for i in range (0,lth):
	tp = ord(string[i])
	tp += offset
	tp += i
	string2.append (chr(tp)) 	#Add each encrypted character to new string. Err, I mean, list.	
print ("Encrypted string is ", end=''),
for i in range (0, lth+1):
	print (string2[i], end='')	#Purge each character into the terminal
print ("")
