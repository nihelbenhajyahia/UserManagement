Project user management allows to create a user on database and view all détails of users
### Version:
    SpringBoot : 2.7.4  
    java 8+
    Junit4 and Junit5
### steps to execute the project
* Import the project as an existing maven project  
    the source code under src/main/java    
    unit tests under src/test/java   
* launch this commandes:   
   1- update maven project  
   2- mvn clean install   
   3- run as spring boot application   
tomcat default port is 8080  
the application is then up on localhost:8080  
### Api test on postman  
1- url to create a user:
   localhost:8080/user/create  
   you need a request body of type Json  
2- url to display all user details:
   localhost:8080/user/all  
3- url to display detail of user who had id passed on path variable:
   localhost:8080/user/{id}
### Api test on Swagger 
url Swagger: http://localhost:8080/swagger-ui/#/user-controller
