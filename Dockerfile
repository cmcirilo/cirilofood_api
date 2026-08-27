FROM amazoncorretto:11

WORKDIR /app

COPY target/*.jar /app/api.jar

EXPOSE 8080

CMD ["java","-jar","api.jar"]

#create image
#docker image build -t cirilofood_api .

#list images
#docker image ls

#run container using image
#docker container run --rm -p 8080:8080 -e DB_HOST=cirilofood-mysql --network cirilofood-network --name cirilofood_api cirilofood_api
#docker container run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=12345678 --network cirilofood-network --name cirilofood-mysql mysql:8.0

#create network
#docker network create --driver bridge cirilofood-network

#list networks
#docker network ls

#list volumes
#docker volume ls
