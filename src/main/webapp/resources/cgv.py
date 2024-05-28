# 라이브러리 추가
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
import time

# 브라우저 꺼짐 방지 + 자동화 메세지 제거 옵션
chrome_options = Options()
chrome_options.add_experimental_option("detach", True)
chrome_options.add_experimental_option("excludeSwitches", ["enable-automation"])

# 브라우저(크롬) 실행
driver = webdriver.Chrome(options=chrome_options)

# 주소 입력
url = "http://www.cgv.co.kr/movies/?lt=1&ft=0"
driver.get(url)

# 페이지 로딩
time.sleep(2)

# 영화(19개)의 정보를 가져온다
movies = driver.find_elements(By.CSS_SELECTOR,'#contents > div.wrap-movie-chart > div.sect-movie-chart > ol > li')

# 리스트 타입으로 movie_list 생성
movie_list = []


for movie in movies:
    rank = int(movie.find_element(By.CSS_SELECTOR,'div.box-image > strong').text[3:])            # 랭킹
    title = movie.find_element(By.CSS_SELECTOR,'div.box-contents > a > strong').text    # 영화제목
    percent = movie.find_element(By.CSS_SELECTOR,'div.box-contents > div > strong').text    # 예매율
    release_date = movie.find_element(By.CSS_SELECTOR,'div.box-contents > span.txt-info > strong').text    # 개봉일
    poster = movie.find_element(By.CSS_SELECTOR,'div.box-image > a > span > img').get_attribute('src')    # 개봉일
    movie_url = movie.find_element(By.CSS_SELECTOR,'div.box-contents > a').get_attribute('href')


    # 영화 정보 딕셔너리(1개의 영화정보) x 19개
    movie_info = {
        "rank"          : rank, 
        "title"         : title, 
        "percent"       : percent, 
        "release_date"  : release_date, 
        "poster"        : poster,
        "movie_url"     : movie_url
    }
    movie_list.append(movie_info)

detail_list = []
def movie_detail(detail) : 
    
    driver.get(detail['movie_url'])
    time.sleep(1)

    spec = driver.find_element(By.CSS_SELECTOR,' #select_main > div.sect-base-movie > div.box-contents > div.spec').text
    story = driver.find_element(By.CSS_SELECTOR,'#menu > div.col-detail > div.sect-story-movie').text

    detail_info = {
        "rank"          : detail['rank'], 
        "title"         : detail['title'], 
        "percent"       : detail['percent'], 
        "release_date"  : detail['release_date'], 
        "poster"        : detail['poster'],
        "spec"          : spec,
        "story"         : story
    }
    detail_list.append(detail_info)
    

for detail in movie_list:
    movie_detail(detail)


####################################################################################

# 스프링 서버의 엔드포인트
serverURL = "http://localhost:9091/cgv/movies"

# POST 요청으로 데이터 전송
import requests
import json

headers = {'Content-Type' : 'application/json'}
response = requests.post(serverURL, json=detail_list, headers = headers)

# 서버 응답 출력
# response.status_code => 성공 200, 오류 400, 404, 405, 500 등..
if response.status_code == 200:
    print('success!')
else :
    print(response.status_code)