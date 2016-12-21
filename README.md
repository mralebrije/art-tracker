# ART TRACKER (Baltimore City)

This data set shows the location of museums within the City of Baltimore. However, this is not a complete list. 
Museums
Baltimore Arts Organizations

If you are using Maven, you can run the application using ./mvnw spring-boot:run. Or you can build the JAR file with ./mvnw clean package. Then you can run the JAR file:

java -jar target/gs-batch-processing-0.1.0.jar

Congratulations! You built a batch job that ingested data from a spreadsheet, processed it, and wrote it to a database.

https://github.com/socrata/soda-java
## Set DB configuation

### Local configuration
Review application-local.properties file in oder to configure MySQL DB

 1. Create **arttracker** scheme
 2. Set user DB credentials: **art/tracker**
 3. Review DB url connection to: **jdbc:postgresql://localhost:8080/arttracker**

### Production configuration
Review application-prod.properties file in oder to configure MySQL DB

 1. Create **arttracker** scheme
 2. Set user DB credentials
 3. Review DB url connection to: **jdbc:postgresql://localhost:8080/arttracker**

## Run this project

### Run locally

* Run task with Profile: _local_ (this is selected by default)  
**spring-boot:run**

### Run production

* Run task with Profile: _prod_  
**spring-boot:run**

## Generate JAR artifact for this project

### Generate local artifact

* Run task with Profile: _local_ (this is selected by default)  
**package**

* Go to **/tarjet** folder inside project directory and locate **.jar** file  
**Ex. art-tracker-0.0.1-SNAPSHOT.jar**

### Generate prod artifact

* Run task with Profile: _prod_  
**package**

* Go to **/tarjet** folder inside project directory and locate **.jar** file  
**Ex. art-tracker-0.0.1-SNAPSHOT.jar**

## Access JSONDoc

### Access JSONDoc page locally

* Navigate to link
http://localhost:8080/art-tracker/jsondoc-ui.html   
* Write **jsondoc** in text box  
* Press **Get documentation** button  
* Press **Servicios para Trip** link (located at left middle page) 
 
 *You can use JSONDoc http client to test each endpoint

### Access JSONDoc page production

* Navigate to link
http://localhost:8080/art-tracker/jsondoc-ui.html  
* Write **jsondoc** in text box  
* Press **Get documentation** button  
* Press **Servicios para art** link (located at left middle page)  

 *You can use JSONDoc http client to test each endpoint
 
## Access Web Catalog Report

### Access local web catalog

* Navigate to link
http://localhost:8080/art-tracker/

### Access production web catalog

* Navigate to link
http://localhost:8080/art-tracker/



