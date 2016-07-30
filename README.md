# Simple Stock Info App

## Prerequisites

You will need the following things properly installed on your computer.

* [Node.js](http://nodejs.org/) (with NPM)
* [Ember CLI](http://ember-cli.com/)
* [Java](https://java.com/en/download/)

## Running 

* Start Spring Java Server Application
* java -jar Simple-Stock-Info-App\ember-stockclient folder\target\gs-rest-service-0.1.0.jar
*
* Start Ember Client Application
* cd to Simple-Stock-Info-App\ember-stockclient folder
* 'ember server --proxy http://127.0.0.1:8080'
* Visit stock search tool at [http://localhost:4200](http://localhost:4200).

## If you need to compile the Java code use maven
[Maven] https://maven.apache.org/
* cd into Simple-Stock-Info-App\stockserver folder 
* run mvn clean package 
