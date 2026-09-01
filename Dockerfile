FROM amazoncorretto:11

WORKDIR /app

#ARG JAR_FILE

#COPY target/${JAR_FILE} /app/api.jar
COPY target/*.jar /app/api.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

EXPOSE 8080

CMD ["java","-jar","api.jar"]

#create image
#docker image build -t cirilofood-api .

#list images
#docker image ls

#run container using image
#docker container run --rm -p 8080:8080 -e DB_HOST=cirilofood-mysql --network cirilofood-network --name cirilofood-api cmcirilo/cirilofood-api
#docker container run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=12345678 --network cirilofood-network --name cirilofood-mysql mysql:8.0
#docker container run --rm -it --network cirilofood_api_cirilofood-network alpine sh

#create network
#docker network create --driver bridge cirilofood-networka

#list networks
#docker network ls

#list volumes
#docker volume ls

#create tag in image
#docker image tag cirilofood-api:latest cmcirilo/cirilofood-api:latest

#login
#docker login

#push
#docker push cmcirilo/cirilofood-api:latest
