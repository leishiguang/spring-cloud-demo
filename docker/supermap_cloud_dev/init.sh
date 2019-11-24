mkdir -p /root/docker/supermap_onewindow_dev
docker network create one-window
mkdir -p /root/docker/supermap_onewindow_dev/config
chown -R 1100:1100 /root/docker/supermap_onewindow_dev/config
