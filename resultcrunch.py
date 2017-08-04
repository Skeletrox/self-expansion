##################################################################################
# In a fit of self-appreciation, I wrote a script that reads [almost] everyone's #
# grade points in the previous semester's final examination. Let's just say the  #
# opposite of self-appreciation happened.										 #
##################################################################################  
import requests, json, re

def parse(html, string):
	regex = r">([0-9]+[.]?[0-9]?)<"
	try:
		loc = html.index ("Name of the Student :") + len("Name of the Student :") + 1
	except:
		return
	stop = html.index('<', loc)
	name = html[loc:stop]
	print (name + ' ' + string)
	matches = re.findall(regex, html)
	sum_hrs = 0
	sum_act = 0
	for i in range (2, len(matches), 4):
		sum_hrs += float(matches[i-1])
		sum_act += float(matches[i])*float(matches[i-1])
		print (matches[i], end = ' ')
	print ("{0:.2f}".format(sum_act/sum_hrs))
	if (sum_act/sum_hrs) > 10:
		exit()

strs = ["{0:03}".format(i) for i in range(1, 228)]
for string in strs:
	r = requests.get("http://results.logisys.net.in/reva/app.php?a=DisplayStudentResult&r=R14CS"+string+"&e=F")
	data = r.json()
	code = data["data"]["html"]
	parse(code, "R14CS" + string)