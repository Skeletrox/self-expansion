#!/bin/sh/env python3

from random import randint
text = input("Enter string to encrypt: ")
coder = 47
coder = int(coder)
lth  = len(text)
enc = []
for i in range(0,lth):
	if (text[i] == ' '):
		c = 9			#Using ASCII of tab because irl web inputs won't accept tabs while you type your password lol
	else:
		c = ord(text[i])
	c = (c^coder)
	if (c != 9):
		enc.append(chr(c))
	else:
	 	enc.append(' ')
for i in range(0,lth):
	print (enc[i], end='')
print ("")
