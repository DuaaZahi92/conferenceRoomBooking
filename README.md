# Conference Room Booking app

Swagger documentation:
http://localhost:9111/swagger-ui/index.html#/

## To run the application:
* with Docker: 
```bash
    docker build -t duaazahi/conference-room-booking:0.0.1 --no-cache .
    docker run --name conference-room-booking -p 9111:9111 duaazahi/conference-room-booking:0.0.1
```

* without docker: 
```bash
mvn clean install
java -jar target/conferenceRoomBooking-0.0.1.jar 
```
