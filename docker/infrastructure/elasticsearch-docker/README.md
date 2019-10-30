# Elasticsearch

## Start

```bash
# 官方的镜像的网络设置是允许外部访问的即network.host=0.0.0.0
docker run -d -p 9200:9200 \
--name elasticsearch \
 -p 9300:9300 \
 -p 5601:5601 \
 elasticsearch:6.4.0

# 运行es6.4版本并把配置文件挂载出来（方便修改es跨域，head插件需要）
# 如果挂载时出现权限错误，则首先进行目录授权 chmod 777 /home/elasticsearch/data
docker run -d -p 9200:9200 \
  -p 9300:9300 \
  --name elasticsearch \
  -e "discovery.type=single-node" \
  -e "ES_JAVA_OPTS=-Xms512m -Xmx512m" \
  -v /home/elasticsearch/data:/usr/share/elasticsearch/data \
  -v /home/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
  elasticsearch:6.4.0
```

可视化客户端：

```bash
# elasticsearch head
docker run -d --name elasticsearch_head -p 9100:9100 mobz/elasticsearch-head:5

# ElasticHD
docker run -p 9800:9800 -d --link elasticsearch:demo containerize/elastichd
```

参考：
1. [docker安装ES & Kibana](https://www.jianshu.com/p/fdfead5acc23)
2. [一文上手 Elasticsearch常用可视化管理工具](https://www.jianshu.com/p/54e04b5b5ce2)