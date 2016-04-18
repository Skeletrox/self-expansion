# A (Very) simple calculator using Python.
# Asks you to input the required expression with spaces [The spaces are the heart of the program!], performs the operation and gives result
# Coded by the man himself, Sriram Ramaswamy on Python running in Kali Linux on VMWare Workstation Pro`
# Implements string processing, looping and conditionals



expr = raw_input("Enter the expression with spaces between the operators and operand\t")
i=0
while (expr[i]!=' '):
	i=i+1
oper = expr[i+1]
j=i+2
print str(expr)
op1 = int(expr[0:i])
op2 = int(expr[j:])
if (oper == '+'):
	res = op1 + op2
elif (oper == '-'):
	res = op1 - op2
elif (oper == '*'):
	res = op1 * op2
elif (oper == '/'):
	if (op2 != 0):
		res = op1 / op2
	else:
	 	res = 0
	 	print "Dividing by zero, result set to 0"
elif (oper == '%'):
	res = op1 % op2
elif (oper == '^'):
	res = op1 ** op2
else:
	print "Wrong operator, setting result to 0"
print res
