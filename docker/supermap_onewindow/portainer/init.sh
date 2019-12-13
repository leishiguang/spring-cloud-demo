
$ cd /home/docker/images && docker load --input portainer-1.22.2.tar
$ docker volume create portainer_data
$ docker run -d \
  -p 9001:9000 \
  --name supermap.portainer \
  --restart always \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v portainer_data:/data \
  reg.chiq-cloud.com/arm64v8/portainer:1.22.2