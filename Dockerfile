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
#docker container run --rm -p 8080:8080 cirilofood_api
