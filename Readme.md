sudo docker run -d --hostname my-rabbit --name some-rabbit -p 7777:15672 -p 5672:5672 -p 15674:15674 myrabbitmqimage:v1