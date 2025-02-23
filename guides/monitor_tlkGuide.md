Grafana+prometeus
elasticsearch logstash kibana

## Что сделал

### Grafana+prometeus установил локально
Нужно добавить строки management в application.yaml (как минимум), создал yaml файл в папке с установленным prometeus:


### elasticsearch logstash kibana в docker-compose-elk.
Нужно создать файлы: logback-spring.xml, logstash.confс соответствующим кодом.


## Нужно в идеале сделать: поднять все docker-compose в infra, настроить доступ ко всем микросервисам. Создать нужные dashbords и наблюдать.