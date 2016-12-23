# ART TRACKER (Baltimore City)

 Art Tracker shows location of museums within Baltimore City, this allows you to make **CRUD** operations, apply search, filters and ordering over museums.
 Also you can explore which other Art Organizations within Baltimore City are close to each museum selected and visualize them into a Google Map. By last you
 can visualize in a 
 
## Why Art Tracker?

 **1. First chance:** 
 
 _Are you an Art Lover?..._ 
 _Do you live in Baltimore City?..._ 
 _Do you like Museums and Art Organizations?_ 
  
 You have to explore this beautiful set of data about art locations in Baltimore.
 
 **2. Second chance ~~(Mmmm, let´s try again)~~:** 
 
 _Are you a software developer?..._ 
 _Are you looking for a Java Full Stack Project example?..._
 _RESTful, services, controllers, ORM, angular, spring, hibernate, loombok, mysql, h2 are words that you have in mind?_ 
  
  Perfect you can explore this code and love it or hate it ~~(as well as me these nights)~~. 
  
 **3. Third chance ~~(last chance,I have to be really careful selecting my next words to keep your attention in my code)~~:**
 
 _Are you a Talent hunter?..._
 _Are you looking for a committed and passionate worker in tour team?_  
 
 I introduce myself with this code for you like first step, hoping to make **great things** together!

## Data sets

  Data was retrieved from OpenData Baltimore

 * Baltimore Arts Organizations (https://data.baltimorecity.gov/Culture-Arts/Baltimore-Arts-Organizations/r4ur-u5nm)
 * Museums (https://data.baltimorecity.gov/Culture-Arts/Museums/8hgq-9pi6)
 
## Entire development stack
 
 * [Java](https://www.java.com/)
 * [Angular](https://angularjs.org), [Spring](http://docs.spring.io/), [Spring boot](http://docs.spring.io/spring-boot/), [Hibernate](http://projects.spring.io/spring-data/) 
 * [MySQL](https://www.mysql.com/), [H2](www.h2database.com)
 * [Maven](https://maven.apache.org/)
 * [ORM](hibernate.org/orm/what-is-an-orm/), [MVC](https://www.tutorialspoint.com/mvc.../)
 * [Socrata SODA API](https://github.com/socrata/soda-java/), [Google Maps API](https://developers.google.com/maps/)


## How to run?

You can run this project using 2 profiles: **local**(_default_, using H2 DB), **prod**(using MySQL)

###### _Prerequisites_
 * Maven installed
 * (Optional) MySQL configuration (see configurations steps below)
 
###### _Deployment_
You can run the application using maven configuration command:
 ```spring-boot:run.```

Or you can build the JAR file with maven configuration command:
  ```clean package```
  
Then you can execute the runnable JAR generated:
  ```java -jar target/art-tracker-0.0.1-SNAPSHOT.jar```

## DB configuation

###### _Local configuration_
     
* **local** Profile is set by default in this project, so H2 DB will be created, then you don´t need any DB configuration

###### _Production configuration_

* **prod** Profile needs to be indicated when run maven task, so be sure to add profile in your configuration task.

Review **application-prod.properties** file in oder to configure MySQL DB

 1. Create **arttracker** scheme
 2. Set user DB credentials
 3. Review DB url connection to: **jdbc:postgresql://localhost:8080/arttracker**
 
## Visualization Web Art Tracker

### Access local WEB

* Navigate to link
http://localhost:8080/art-tracker/

### Access cloud WEB

* Navigate to link
http://link/art-tracker/


### Access JSONDoc

###### _Access JSONDoc page locally_

 You can use JSONDoc in order to visualize API documentation, also you are able to use the http client in order to consume and test each endpoint available.
 
* Navigate to link
http://localhost:8080/art-tracker/jsondoc-ui.html  
* Write **jsondoc** in text box  
* Press **Get documentation** button  
* Press **Museum Services** or **Organization Services** link (located at left middle page) 
 
## BONUS: Android App


