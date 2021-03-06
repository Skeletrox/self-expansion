import socket
import sys
from _thread import *

HOST = ''	#Symbolically means all available interfaces
PORT = 8888	#Arbitrary non-privileged port

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print ('Socket Created')

#Bind socket to local host and port
try:
	s.bind((HOST, PORT))
except socket.error as msg:
	print ('Bind error: ' + str(msg[0]) + ' Message ' + msg[1])
	sys.exit()

print ('Socket bind complete')

#start listening on socket
s.listen(10)



#Function to handle connections
def clientthread (conn):
#Sending messages to connected client
	conn.send(bytes('Welcome to the server. Please type something and hit enter thanks\n', 'utf-8'))

#Keep listening my boy
	while True:
		
#Receiving from client
		data = (conn.recv(1024))
		reply = "I got " + data.decode('utf-8')
		if not data:
			break
		conn.sendall(bytes(reply, 'utf-8'))
		
#Come out of loop
	conn.close()

#Keep talking to client now
while 1:
	#wait to accept a connection - blocking call
	conn, addr = s.accept()
	print ("Connected with " + addr[0] + ": " + str(addr[1]))

#Start new thread takes first argument as function to be run , second is the tuple of arguments to the function
	start_new_thread(clientthread ,(conn,))

s.close()
