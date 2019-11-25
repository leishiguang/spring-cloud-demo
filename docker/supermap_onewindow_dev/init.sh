
## 上传完所有文件

mkdir -p /root/docker/supermap_onewindow_dev
docker network create one-window
mkdir -p /root/docker/supermap_onewindow_dev/config
chown -R 1100:1100 /root/docker/supermap_onewindow_dev/config

## 部署完 influxdb

docker exec -it supermap.influxdb influx

#进入后：
show databases
show users
# 创建数据库
create database "test"
# 创建用户
create user "testuser" with password 'testpassword' with all privileges
create user "root" with password 'root' with all privileges