# MySQL 5.7

## official 

官方镜像，来源 [https://github.com/docker-library/mysql](https://github.com/docker-library/mysql)

### Start

```bash
docker run -d -p 3306:3306 \
  --name mysql-5.7 \
  --privileged=true \
  -v /home/mysql/5.7/official/data:/var/lib/mysql \
  -v /home/mysql/5.7/official/config/:/etc/mysql \
  -e MYSQL_ROOT_PASSWORD=eVoATqQM1VL4ysos \
  -e MYSQL_ROOT_HOST='%' \
  mysql:5.7 \
  --character-set-server=utf8mb4 \
  --collation-server=utf8mb4_unicode_ci
```

## master & slave 自定义镜像

### Start master

```bash
docker run -d -p 3306:3306 \
  --name mysql-5.7-master \
  -e MYSQL_DATABASE=web \
  -e MYSQL_USER=web \
  -e MYSQL_PASSWORD=web \
  -e MYSQL_ROOT_PASSWORD=root_password \
  -e MYSQL_REPLICATION_USER=user_for_slave \
  -e MYSQL_REPLICATION_PASSWORD=user_password_for_slave \
  leishiguang/mysql:5.7
```

### Start slave

```bash
docker run -d -p 3307:3306  \
  --name mysql-5.7-slave \
  -e MYSQL_MASTER_HOST=mysql_master \
  -e MYSQL_MASTER_PORT=3306 \
  -e MYSQL_ROOT_PASSWORD=root_password \
  -e MYSQL_REPLICATION_USER=user_for_slave \
  -e MYSQL_REPLICATION_PASSWORD=user_password_for_slave \
  --link mysql_master:mysql_master \
  leishiguang/mysql:5.7
```

### Check

```
docker exec -i mysql-5.7-master mysql -u web -pweb -D web -e "CREATE TABLE names(id INT AUTO_INCREMENT KEY, name VARCHAR(10));INSERT INTO names (name) VALUES ('test1'), ('test2');"
```

### Docker Compose

```yaml
version: '2'

services:
    db:
        image: mishamx/mysql:5.7
        ports:
            - "3306:3306"
        environment:
            MYSQL_DATABASE: web
            MYSQL_USER: web
            MYSQL_PASSWORD: web
            MYSQL_ROOT_PASSWORD: root_password
            MYSQL_REPLICATION_USER: user_for_slave
            MYSQL_REPLICATION_PASSWORD: user_password_for_slave
        networks:
            - back

    db-slave:
        image: mishamx/mysql:5.7
        ports:
            - "3307:3306"
        environment:
            MYSQL_ROOT_PASSWORD: root_password
            MYSQL_MASTER_HOST: db
            MYSQL_MASTER_PORT: 3306
            MYSQL_REPLICATION_USER: user_for_slave
            MYSQL_REPLICATION_PASSWORD: user_password_for_slave
        networks:
            - back
        links:
            - db
        depends_on:
            - db

networks:
    front:
        driver: bridge
    back:
        driver: bridge
```