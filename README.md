# Simple Stock Info App

## Prerequisites

You will need the following things properly installed on your computer.

* [Node.js](http://nodejs.org/) (with NPM)
* [Ember CLI](http://ember-cli.com/)
* [Java](https://java.com/en/download/)
* [Bower](http://bower.io/)

## Setup
* `git clone <repository-url>` this repository
* change into the ember-stockclient directory
* `npm install`
* `bower install`

## Important Step After Setup

* unzip ember-google-charts-mod.zip found in ember-stockclient directory
* replace ember-stockclient\node_modules\ember-google-charts with ember-google-charts folder from zip

## Running 
* Open 2 terminal or command windows 

* In window 1
* Start Spring Java Server Application
* java -jar stockserver\target\gs-rest-service-0.1.0.jar

* In window 2
* Start Ember Client Application
* cd to ember-stockclient directory
* 'ember server --proxy http://127.0.0.1:8080'
* Visit stock search tool at [http://localhost:4200](http://localhost:4200).

## If you need to compile the Java code use maven
[Maven] https://maven.apache.org/
* cd into Simple-Stock-Info-App\stockserver folder 
* run mvn clean package 
