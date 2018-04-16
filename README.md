# FiiPracticSpring2018

Spring boot 2.0 rest application made during Fii practic 2018 Yonder java training sessions.
    
## Getting Started
    
You can download/clone/fork this project for teaching purposes.
    
* Download: https://github.com/MariusDanutIancu/FiiPracticSpring2018/archive/master.zip
* Clone: `git clone https://github.com/MariusDanutIancu/FiiPracticSpring2018.git`
    
## Prerequisites

### This project requires following technologies:
* JAVA jdk8: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
* Maven: https://maven.apache.org/download.cgi
* Postgre 9.4.17: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
    
### Optional:
* Python 3.5+ to run scripts: https://www.python.org/downloads/
* Intellij recommended IDE: https://www.jetbrains.com/idea/download/#section=windows
* git bash: https://git-scm.com/downloads
    
## Installing
* JDK Instalation: (https://www.wikihow.com/Install-the-Java-Software-Development-Kit)
* java_home varible: (https://www.mkyong.com/java/how-to-set-java_home-on-windows-10/)
* Maven instalation: (https://www.mkyong.com/maven/how-to-install-maven-in-windows/)

## Usage  

You can test api requests at the following link on a local server: `http://localhost:8080/api/<api_version>/<resource>`
### Examples:
* Doctor get request (http://localhost:8080/api/0.1/doctors)
* Doctor get request on id: (http://localhost:8080/api/0.1/doctors/1)
* Doctor post request: (http://localhost:8080/api/0.1/doctors) 
```json
{
   "address": {
      "city": "string",
      "country": "string",
      "county": "string",
      "postal_code": "string",
      "state": "string",
      "street": "string"
    },
   "email": {
      "email": "string",
   },
   "firstName": "string",
   "function": "string",
   "lastName": "string",
   "phoneNumber": {
      "phoneNumber": "string"
    }
}    
```   
* Patient get request (http://localhost:8080/api/0.1/patients)
* Patient get request on id: (http://localhost:8080/api/0.1/patients/1)
* Patient post request: (http://localhost:8080/api/0.1/patients) 
```json
{
   "address": {
     "city": "string",
     "country": "string",
     "county": "string",
     "postal_code": "string",
     "state": "string",
     "street": "string"
   },
   "age": 0,
   "email": {
     "email": "string",
   },
   "firstName": "string",
   "lastName": "string",
   "patient_id": 0,
   "phoneNumber": {
     "phoneNumber": "string"
   }
} 
```    
* Appointment get request (http://localhost:8080/api/0.1/appointments)
* Appointment get request on id: (http://localhost:8080/api/0.1/appointments/1)
* Appointment get request with filter: (http://localhost:8080/api/0.1/appointments?doctorid=1)
* Future appointment get request: (http://localhost:8080/api/0.1/appointments/future_appointments)
* Future appointment get request with filter: (http://localhost:8080/api/0.1/appointments/future_appointments/filter?doctorid=1)  
* Appointment post request: (http://localhost:8080/api/0.1/appointments)
```json
{
   "cancel": true,
   "cause": "string",
   "doctor_id": 0,
   "endTime": "2018-04-16T14:07:34.489Z",
   "patient_id": 0,
   "startTime": "2018-04-16T14:07:34.489Z"
}    
```   
* Appointment put future appointments request: http://localhost:8080/api/0.1/appointments/cancel_appointment)
```json
{
   "appointment_id": 0,
   "cancel": true,
   "doctor_id": 0,
   "patient_id": 0,
}
```   
## Running the tests   
    
Placeholder.
    
## Tutorials
   
* Rest: 
    * https://blog.mwaysolutions.com/2014/06/05/10-best-practices-for-better-restful-api/
    * http://www.andrewhavens.com/posts/20/beginners-guide-to-creating-a-rest-api/
* Spring: 
    * https://dzone.com/articles/a-guide-to-spring-framework-annotations 
    * http://www.baeldung.com/spring-data-rest-relationships
* Hibernate: 
    * http://hibernate.org/
    * https://howtoprogramwithjava.com/hibernate-eager-vs-lazy-fetch-type/   

## Contributing

1. Fork
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request

## Authors
    
Placeholder.