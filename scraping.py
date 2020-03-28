import requests
import urllib.request
import time
from bs4 import BeautifulSoup

#  https://en.wikipedia.org/wiki/Special:RandomInCategory/

def is_category(link):
	tokens = link.split("/")
	last_token = tokens[len(tokens) - 1].split(":")
	if (last_token[0] == 'Category'):
		return True
	return False

url = "https://en.wikipedia.org/wiki/Special:RandomInCategory/Computers"

response = requests.get(url)

if(is_category(response.url)):
	exit()
	
soup = BeautifulSoup(response.text, "html.parser")

#print(soup.find(id="content").get_text())
#print(soup.get_text())

p = soup.find('p')
text = 'initial text'#first_p.find_next('p')
while True:
	if(p == None):
		break
	p = p.find_next('p')

'''print(soup.title)
print(response.url)'''
