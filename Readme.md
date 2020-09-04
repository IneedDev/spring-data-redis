**Redis ubuntu installation:**

wget http://download.redis.io/redis-stable.tar.gz

tar xvzf redis-stable.tar.gz

cd redis-stable

make

--------

sudo cp src/redis-server /usr/local/bin/

sudo cp src/redis-cli /usr/local/bin/


**start redis**

redis-server

redis-cli ping

redis-cli

redis-cli shutdown

***Local Kafka***

https://docs.confluent.io/current/quickstart/ce-docker-quickstart.html#ce-docker-quickstart

http://localhost:9021/