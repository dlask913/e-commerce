import json
import random

from locust import HttpUser,SequentialTaskSet,task,between

class UserBehavior(HttpUser):

    def on_start(self):
        print("start test")

    def on_stop(self):
        print("end test")

    @task # 로그인 -> 상품 리스트 확인 -> 상품 주문 -> 주문한 상품 확인
    class SequenceOfTasks(SequentialTaskSet):
        wait_time = between(1, 3)

        @task
        def home(self):
            self.client.get('/member-service/main')

        @task
        def login_member(self):
            random_value = [str(i) for i in range(1, 101)]
            random_choice = random.choice(random_value)
            userId_value = "userId" + random_choice
            pwd_value = "pwd" + random_choice
            self.client.post("/member-service/login", data={"userId": userId_value, "pwd": pwd_value})

        @task
        def view_catalog(self):
            random_value = [str(i) for i in range(1, 101)]
            random_choice = random.choice(random_value)
            userId_value = "userId" + random_choice
            self.client.get(f'/order-service/orders/{userId_value}')

        @task
        def order_catalog(self):
            random_value = [str(i) for i in range(1, 101)]
            random_choice = random.choice(random_value)
            userId_value = "userId" + random_choice
            catalog_value = "CATALOG-00"+str(random.randrange(1,11))
            self.client.post(f'/order-service/orders/{userId_value}/{catalog_value}', data={"qty": random.randrange(1,10)})

        @task
        def get_user_detail(self):
            userId_value = ["userId" + str(i) for i in range(1, 101)]
            userId_str = random.choice(userId_value)
            self.client.get(f'/member-service/{userId_str}')



