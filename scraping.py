import requests
import urllib.request
import time
import json
from bs4 import BeautifulSoup

#  https://en.wikipedia.org/wiki/Special:RandomInCategory/

def is_category(link):
	tokens = link.split("/")
	last_token = tokens[len(tokens) - 1].split(":")
	if (last_token[0] == 'Category'):
		return True
	return False

url_computers = "https://en.wikipedia.org/wiki/Special:RandomInCategory/Computers"
url_biology = "https://en.wikipedia.org/wiki/Special:RandomInCategory/Biology"
url_politics = "https://en.wikipedia.org/wiki/Special:RandomInCategory/Politics"

documents = 0
url_list = []
coprus = []

while documents < 6000:
	
	'''
	if documents < 2000:
		response = requests.get(url_politics)
		if documents == 1000:
			print('1000 docs')
	
	elif documents >= 2000 and documents < 4000:
		response = requests.get(url_biology)
		if documents == 2000:
			print('2000 docs')
	
	else :
		if documents == 4000:
			print('4000 docs')
		response = requests.get(url_computers)
	'''
	#################### URL for RANDOM ARTICLE ###########################
	response = requests.get('https://en.wikipedia.org/wiki/Special:Random')
	#################### URL for RANDOM ARTICLE ###########################	

	if((is_category(response.url) or (response.url in url_list))):
		continue
	else:
		url_list.append(response.url)
		soup = BeautifulSoup(response.text, "html.parser")
		p = soup.find('p')
		#print(p)
		#print(str(documents + 1) + ': ' + soup.title.get_text())
		text = ''
		
		while True:
			if(p == None):
				break
			#print(p.get_text())
			
			text += p.get_text()
			p = p.find_next('p')
		
		x = {
			"link": response.url,
			"title": soup.title.get_text(),
			"text": text
		}
		
		coprus.append(x)
		documents += 1
		print(documents)
#y = json.dumps(coprus)
with open('corpus.json', 'w') as outfile:
    json.dump(coprus, outfile)

#print(url_list)
#print(soup.find(id="content").get_text())
#print(soup.get_text())
	
'''print(soup.title)
print(response.url)'''
