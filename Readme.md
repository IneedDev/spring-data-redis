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


# redis.conf

##   save ""

save 900 1
save 300 10
save 60 10000


stop-writes-on-bgsave-error yes

## The filename where to dump the DB

dbfilename dump.rdb


maxclients 10000


In short... if you have replicas attached it is suggested that you set a lower
limit for maxmemory so that there is some free RAM on the system for replica
output buffers (but this is not needed if the policy is 'noeviction').

maxmemory <bytes>