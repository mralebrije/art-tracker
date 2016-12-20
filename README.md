# ART TRACKER (Baltimore City)

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



