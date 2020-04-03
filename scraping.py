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

while documents < 30:
	
	if documents < 10:
		response = requests.get(url_computers)
	
	elif documents >= 10 and documents < 20:
		response = requests.get(url_biology)
	
	elif documents >= 20 and documents < 30:
		response = requests.get(url_politics)
		
	if(is_category(response.url)):
		continue
	
	if(response.url in url_list):
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
		
		y = json.dumps(x)
		print(y)
		print('------------------------------------------------------')
		documents += 1

#print(url_list)
#print(soup.find(id="content").get_text())
#print(soup.get_text())
	
'''print(soup.title)
print(response.url)'''
