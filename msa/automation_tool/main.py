import time
from selenium import webdriver
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()

url='http://43.200.95.178:8000/member-service/new'
driver.get(url)
for i in range(1,11):
    driver.get(url)
    elm = driver.find_element(By.ID,'userId')
    elm.send_keys('test'+str(i))
    elm = driver.find_element(By.ID,'name')
    elm.send_keys('홍길동'+str(i))
    elm = driver.find_element(By.ID,'pwd')
    elm.send_keys(i)
    but = driver.find_element(By.XPATH,'/html/body/div/form/button')
    but.click()
time.sleep(50)
