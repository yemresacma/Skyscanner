# Finartz Backend Case Documentation

## Sql tables

#### Airport
| Id | airportName |
| ------ | ------ |
|  
#### Company
| Id | companyName |
| ------ | ------ |
|  ||

#### Route
| Id | departurePoint | arrivalPoint |
| ------ | ------ | ------ |
|  

- departurePoint and arrivalPoint are instance of Airport table

#### Flight
| Id | initialTicketPrice | totalSeat | soldTicketNumber | date | company | route |
| ------ | ------ | ------ | ------ | ------ | ------ | ------ |
|
- company is instance of Company object
- route is instance of Route object

#### Ticket
| Id | creditCardInfo | ticketPrice | flightInfo |
| ------ | ------ | ------ | ------ |
|

- creditCardInfo is kept masked according to requirement
- creditCardInfo is kept to be able to return payment for ticket  
- flightInfo is instance of Flight object

## Instructions to initialiaze database
- Import "DatabaseInitializer.postman_collection.json" to Postman and run the collection
- This collection creates 3 airport, 3 company, 4 route, and 2 different flight.
- Import "TicketBuyer.postman_collection.json" and run with different iterations and observe ticket prise raise
- For the rest of the tests, you can follow the rest of the documentation. You can change parameters and make your own tests.

### Airport http requests
##### Get request
- Get all airports using http://localhost:8080/airport
- Get airport by id using http://localhost:8080/airport/id=1 
- Get airport by name using http://localhost:8080/airport/name=istanbul 

##### Delete request
- Delete airport by id using http://localhost:8080/airport/id=1 

##### Post request
- Save airport using http://localhost:8080/airport with json object below
```sh
{
    "name": "adana" 
}
```

### Company http requests
##### Get request
- Get all companies using http://localhost:8080/company
- Get company by id using http://localhost:8080/company/id=1 
- Get company by name using http://localhost:8080/company/name=pegasus

##### Delete request
- Delete company by id using http://localhost:8080/company/id=1

##### Post request
- Save company using http://localhost:8080/company with json object below
```sh
{
    "name": "kamilkoc" 
}
```

### Route http requests
##### Get request
Note that, if there is route information between A and B. It does not mean there is a flight. It only means, there is permission to create a flight between that points.
- Get all routes using http://localhost:8080/route
- Get company by departure and arrival points using http://localhost:8080/route/from=istanbul&to=izmir

##### Post request
- Save route using http://localhost:8080/route with json object below
```sh
{
    "departurePoint": {
        "id": 1
    },
    "arrivalPoint": {
        "id": 3
    }
}
```




### Flight http requests
##### Get request
Note that, Flight must be scheduled at least 10 days before
- Get all flights using http://localhost:8080/flight
- Get available flights by departure and arrival points using http://localhost:8080/flight/from=istanbul&to=izmir

##### Post request
- Save route using http://localhost:8080/route with json object below
```sh
{
    "initialTicketPrice": 100,
    "totalSeat": 100,
    "date": "2022-05-12 12:00:00",
    "company": {
        "id": 1
    },
    "route": {
        "id": 4
    }
}
```

### Ticket http requests
##### Get request

- Get all tickets using http://localhost:8080/ticket
- Get ticket by ticket number using http://localhost:8080/ticket/ticketNumber=1

##### Post request
- Buy ticket with json object below. Note that, card number and flight information will be checked.
```sh
{
    "flightInfoId": 1,
    "creditCardNumber": "1234,1234,1234,1234"
}
```

##### Delete request
- Return ticket with json object below. Note that, flight
```sh
{
    "flightInfoId": 1,
    "creditCardNumber": "1234,1234,1234,1234"
}
```

