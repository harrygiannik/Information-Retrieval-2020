import requests
import urllib.request
import time
import json
from bs4 import BeautifulSoup

def is_category(link):
	tokens = link.split("/")
	last_token = tokens[len(tokens) - 1].split(":")
	if (last_token[0] == 'Category'):
		return True
	return False

documents = 0
url_list = []
coprus = []

while documents < 6000:
	
	#################### URL for RANDOM ARTICLE ###########################
	response = requests.get('https://en.wikipedia.org/wiki/Special:Random')
	#################### URL for RANDOM ARTICLE ###########################	

	if((is_category(response.url) or (response.url in url_list))):
		continue
	else:
		url_list.append(response.url)
		soup = BeautifulSoup(response.text, "html.parser")
		p = soup.find('p')
		text = ''
		
		while True:
			if(p == None):
				break
			
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

with open('corpus.json', 'w') as outfile:
    json.dump(coprus, outfile)
