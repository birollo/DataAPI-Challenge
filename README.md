# DataAPI-Challenge

## Getting Started

### Database Settings:
To create the database you need to have installed a mysql command-line program:
- Create database `CREATE DATABASE dataAPI_db`
- Create user `CREATE USER 'dataAPIManager'@'localhost' IDENTIFIED BY '123465';`
- Grant privileges for user `GRANT ALL PRIVILEGES ON dataAPI_db.* TO 'dataAPIManager'@localhost IDENTIFIED BY '123465';`

### How to run the application
- Start SpringBoot application `mvn spring-boot:run`

### Set database in IntelliJ IDEA
- Click plus (+) button on database tab (if u don't have the database tab go to view -> tool window, if "Database Tools and SQL" plugin is enabled, you will find a menu "database", so click on it to open the correspending panel  )
- Select DataSource -> MariaDB
- URL `jdbc:mariadb://localhost:3306/dataAPI_db`
- username `dataAPIManager`
- password `123465`

## Deploy
I'm not sure about this part because i couldn't test.
I set everything to deploy automatically in production enviroment after a push on the master branch.

### Requirements:
- Jenkins pipeline with hook to master branch
- Technical Docker user (to be set in cf/vars-dev.yml and cf/vars-prod.yml)
- A production space on Cloud


### Allocate Memory
Since the application is very simple I decided to allocate the minimum available memory.
When your app is running, you can use the cf app APP-NAME command to see memory utilization.

`cf push -m 64M` (can be 64M, 128M, 256M, 512M, 1G, or 2G)

### Deploy Application
`cf push` (By default, the cf push command uses the manifest.yml file in the app directory)

### Create service
- create cluster `cf create-service mariadb-k8s xsmall DataAPI_db-cluster`
- get cluster id `cf service DataAPI_db-cluster --guid`
- create db service and bind to cluster `cf create-service mariadb-k8s-database default  DataAPI_db -c '{"parent_reference": "clusterId"}'`
- bind application to database `cf bind-service DataAPI-Challenge  DataAPI_db`

## Decisions made

### Database
I have decided not to use an in-memory database for the following reasons:
- The dataModel is very simple (In-memory systems can help because they are highly optimized for complex data processing)
- I assume that the amount of data will not be very large, and if the data becomes many, I personally believe that using an in-memory database for a small application (like this one) requires unnecessary additional costs
- I assume that the request from data scientists to retrieve data to improve the chatbot doesn't happen very often


### Model
- I decide to put the consent value on the Dialog and not on Costumer, this is to give the customer the possibility to decide which dialogs to give consent to.
### Controller
- This application has only three simple endpoints, so I decided to only have one Controller class.

### Data
Whenever the customer creates a Dialog, the application save it with the consent set to false
  - Consequently, so that the customer does not give his consent, the data will never be taken by the data scientists
  - The data will remain in the database until it receives consent. For this reason, a store procedure could be envisaged that removes all dialogs without consent after N days from creation.


## how to improve the system when there are millions of datapoints in the system

Before going to increase the memory and / or upgrade the CPU (which create additional costs), I would try to index the two tables:
- Customer table indexed on CustomerId 
- Dialog tavle indexed on Language
I choose these two columns because are the ones required in get /data

