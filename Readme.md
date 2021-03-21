
# A Backend API layer to manage bookings.

## Aim
1. A REST layer to manage booking operations.
2. Use of message broker to manage events.

## Assumptions
1. The Booking ID is passed when calling the REST layer.
2. The Bookings created will not be retrieved from the REST layer since data is passing only one way down to the consumers.
4. For updating and deleting data, the Booking information to be passed to the API layer is retrieved from somewhere else.

## Build

1. First build the project to generate a Fat Jar & build the docker image using the Jar file.

gradle build && docker build -t java-app/latest .


sudo docker run -d --hostname my-rabbit --name some-rabbit -p 7777:15672 -p 5672:5672 -p 15674:15674 myrabbitmqimage:v1

## Additional Checks
-----------------
1. To check if Rabbit instance is running

docker ps -a

## Booking API

 - `POST    /v1/movie/create`
 - `GET     /v1//movie/all`
 - `GET     /v1//movie/{movieId}`
