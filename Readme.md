
# A Backend API layer to manage bookings.

## Aim
1. A REST layer to manage booking operations.
2. Use of message broker to manage events.

## Assumptions
1. The Booking ID is passed when calling the REST layer even when it is created (mainly because of Point 2).
2. The Bookings created will not be retrieved from the REST layer since data is passing only one way down to the consumers.
3. For updating and deleting data, the Booking information to be passed to the API layer is retrieved from somewhere else.
4. There are no checks when you create the object if the ID already exists, but just updates the product. The same applies to Update and Delete.
In a real life product more checks should be made.
5. There are no automated tests yet, though have been working on doing a test for the controller. The queue operations have been tested manually and the mappings checked from RabbitMQ admin
6. If you need to check the code in IntelliJ it's preferable to use the Lombok plugin for intellisense.

## Build

1. Create the RabbitMQ Image and start docker

sudo docker run -d --hostname my-rabbit --name some-rabbit -e RABBITMQ_DEFAULT_VHOST=/ -p 15672:15672 -p 5672:5672 -p 15674:15674 myrabbitmqimage:v1

2. First build the project  to generate a Fat Jar

mvn build install


## Additional Checks
-----------------
1. To check if Rabbit instance is running

docker ps -a

## Booking API

 - `POST    /v1/booking/create`

 - `POST    /v1/booking/{bookingId}/update`

 - `POST    /v1/booking/{bookingId}/delete`


 Example of the Payload for create and update:
 {"id":"Booking1","passengerName":"John Grisham","passengerContactNumber":"+044 12345678","pickupTime":"","asap":true,"waitingTime":null,"numberOfPassengers":2,"price":10,"rating":0,"createdOn":"","lastModifiedOn":"","tripWaypoints":[{"id":0,"locality":null,"latitude":0.0,"longitude":0.0},{"id":0,"locality":null,"latitude":0.0,"longitude":0.0}]}
