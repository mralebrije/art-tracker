# ART TRACKER (Baltimore City)

 Art Tracker shows you the location of museums within Baltimore City, this application allows you to:
 
  * Make **CRUD** operations over museums elements
  * Apply searching, filtering and ordering over museums grid
  * Hide and show museums grid
  * Reload grid data
  * Use pagination features
  
  ![alt tag](https://dl.dropbox.com/s/g3oz89f0hexb1c1/art-tracker-1.png)
  
 Also you can:
 
  * Explore which other Art Organizations within Baltimore City are close to each museum selected
  * Visualize details about each organization from list
  * Visualize organizations position into a Google Map
  
  ![alt tag](https://dl.dropbox.com/s/1oe8vs5wolmw3vk/art-tracker-2.png)
 
 By last Art-Tracker shows you 2 charts showing you:
 
  * The number of Museums and Art Organizations grouped By ZIP CODE in a bar chart
  * The number of Museums located in each Baltimore council district in a polar area chart
  * Some statistics about which council district or zip code contains more Museums and Art Organizations
 
 ![alt tag](https://dl.dropbox.com/s/thuki9o0hf77u66/art-tracker-3.png)
 ![alt tag](https://dl.dropbox.com/s/soepi0kozc8zz48/art-tracker-4.png)
 
## Why Art Tracker?

 **1. First chance:** 
 
 _Are you an Art Lover?..._ 
 _Do you live in Baltimore City?..._ 
 _Do you like Museums and Art Organizations?_ 
  
 You have to explore this beautiful set of data about art locations in Baltimore.
 
 **2. Second chance ~~(Mmmm, let´s try again)~~:** 
 
 _Are you a software developer?..._ 
 _Are you looking for a Java Full Stack Project example?..._
 _RESTful, services, controllers, ORM, angular, spring, hibernate, loombok, mysql, h2, integration testing are words that you have in mind?_ 
  
  Perfect you can explore this code and love it or hate it ~~(as well as me these nights)~~. 
  
 **3. Third chance ~~(last chance,I have to be really careful selecting my next words to keep your attention in my code)~~:**
 
 _Are you a Talent hunter?..._
 _Are you looking for a committed and passionate worker in our team?_  
 
 I use this code as first step to introduce myself with you, hoping to make **great things** together!

## Data sets

  Data was retrieved from OpenData Baltimore

 * Baltimore Arts Organizations (https://data.baltimorecity.gov/Culture-Arts/Baltimore-Arts-Organizations/r4ur-u5nm)
 * Museums (https://data.baltimorecity.gov/Culture-Arts/Museums/8hgq-9pi6)
 
## Development stack
 
 * [Java](https://www.java.com/)
 * [Angular](https://angularjs.org), [Spring](http://docs.spring.io/), [Spring boot](http://docs.spring.io/spring-boot/), [Hibernate](http://projects.spring.io/spring-data/) 
 * [MySQL](https://www.mysql.com/), [H2](www.h2database.com)
 * [Maven](https://maven.apache.org/)
 * [ORM](hibernate.org/orm/what-is-an-orm/), [MVC](https://www.tutorialspoint.com/mvc.../)
 * [Socrata SODA API](https://github.com/socrata/soda-java/), [Google Maps API](https://developers.google.com/maps/)
 * [ui-grid](http://ui-grid.info/docs/), [angular schema form](https://github.com/json-schema-form/angular-schema-form), [Angular Chart](https://jtblin.github.io/angular-chart.js/)
 * [REST-assured ](https://github.com/rest-assured/rest-assured)

## How to run?

You can run this project using 2 profiles: **local**(_default_, using H2 DB), **prod**(using MySQL)

###### _Prerequisites_
 * Maven installed
 * (Optional) MySQL configuration (see configurations steps below)
 
###### _Deployment_
You can run the application using maven configuration command:

```
spring-boot:run
```

Or you can build the JAR file with maven configuration command:
 ```
 clean package
 ```
  
Then you can execute the runnable JAR generated:
 ```
  java -jar target/art-tracker-0.0.1-SNAPSHOT.jar
 ```
 
## How to run integration tests?

 To run your integration tests, you can either choose to:
  
###### _run the tests within your IDE (see screenshot below)_
 
![alt tag](https://dl.dropboxusercontent.com/s/8l7bpg4lc6g7a8p/run-tests.png)
 
###### _run with maven Maven simply executing the following command within a terminal:_
  
```
 mvn integration-test
```


## DB configuation

###### _Local configuration_
     
* **local** Profile is set by default in this project, so H2 DB will be created, then you don´t need any DB configuration

###### _Production configuration_

* **prod** Profile needs to be indicated when run maven task, so be sure to add profile in your configuration task.

Review **application-prod.properties** file in oder to configure MySQL DB

 1. Create **arttracker** schema
 2. Set user DB credentials
 3. Review DB url connection to: **jdbc:mysql://localhost:8080/arttracker**
 
## Visualization Web Art Tracker

### Access local WEB

* Navigate to link
http://localhost:8080/art-tracker/

### Access JSONDoc

###### _Access JSONDoc page locally_

 You can use JSONDoc in order to visualize API documentation, also you are able to use the http client in order to consume and test each endpoint available.
 
* Navigate to link
http://localhost:8080/art-tracker/jsondoc-ui.html  
* Write **jsondoc** in text box  
* Press **Get documentation** button  
* Press **Museum Services** or **Organization Services** link (located at left middle page) 
 
![alt tag]( https://dl.dropbox.com/s/yc3mouej25t2gbk/art-tracker-5.png)
 
## BONUS: Spring for Android App (Coming soon)

![alt tag](http://impossiblesolitaire.com/wp-content/uploads/2015/09/available_google.png)
