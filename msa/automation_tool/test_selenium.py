import time
from selenium import webdriver
from selenium.webdriver.common.by import By

# 스크립트 자동화
driver = webdriver.Chrome()

url='http://43.200.95.178:8000'
driver.get(url+'/member-service/new')
elm = driver.find_element(By.ID,'userId')
elm.send_keys('testId')
elm = driver.find_element(By.ID,'name')
elm.send_keys('testName')
elm = driver.find_element(By.ID,'pwd')
elm.send_keys('testPwd')
but = driver.find_element(By.XPATH,'/html/body/div/form/button')
time.sleep(3)
but.click()
time.sleep(1)

driver.get(url+'/member-service/login')
elm = driver.find_element(By.ID,'userId')
elm.send_keys('testId')
elm = driver.find_element(By.ID,'pwd')
elm.send_keys('testPwd')
but = driver.find_element(By.XPATH,'/html/body/div/form/button')
time.sleep(3)
but.click()
time.sleep(1)

driver.get(url+'/order-service/orders/testId')
time.sleep(3)

driver.get(url+'/order-service/orders/testId/CATALOG-002')
elm = driver.find_element(By.ID,'qty')
elm.send_keys(3)
but = driver.find_element(By.XPATH,'/html/body/div/form/button')
time.sleep(3)
but.click()
time.sleep(1)

driver.get(url+'/member-service/testId')
time.sleep(5)

# url='http://43.200.95.178:8000/member-service/new'
# for i in range(1,101):
#     driver.get(url)
#     elm = driver.find_element(By.ID,'userId')
#     elm.send_keys('userId'+str(i))
#     elm = driver.find_element(By.ID,'name')
#     elm.send_keys('name'+str(i))
#     elm = driver.find_element(By.ID,'pwd')
#     elm.send_keys('pwd'+str(i))
#     but = driver.find_element(By.XPATH,'/html/body/div/form/button')
#     but.click()
