# Price Microservice

> Microservice Demo application using SpringBoot providing a REST endpoint to query price info for a brand, product and
> date in an ecommerce company DB.

## Table of Contents

* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Basic Layers Schema](#basic-layers-schema)
* [Design Comments](#design-comments)
* [Setup](#setup)
* [Usage](#usage)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)

## General Information

We are going to have an in memory DB (H2) to store a PRICES table with the following fields:

- BRAND_ID: foreign key to a brand of the company (For example: "1 = ZARA").
- START_DATE , END_DATE: range of dates on which the price applies for a rate.
- PRICE_LIST: Id of the rate that can be applied.
- PRODUCT_ID: Id of the product.
- PRIORITY: Used when there is more than one price for a search. If there are two (or more) rates for a rate range we
  use the one with higher priority (higher numeric value).
- PRICE: final sale price.
- CURR: currency ISO.

Our REST endpoint will provide a response based on the following:

- Input parameters: date, product ID and brand ID.
- Output Data: product ID, brand ID, rate ID to be applied, range of dates on which price applies for the rate and final
  sale price.

## Technologies Used

- Java - version 17
- Spring Boot - version 3.1.1
- Maven - version 3.8.1
- Lombok - version 1.18.24
- H2 - version 2.2.220
- Mockito - version 4.9.0
- Mockito JUnit Jupiter - version 4.8.1
- springdoc-openapi - version 2.1.0

## Basic Layers Schema

![Basic Layers Schema](./img/Basic_layers_schema.png)

## Design Comments

The project has been designed mainly using hexagonal architecture pattern and DDD (Domain Driven Design).

The idea is the be able to change/test components easily on different layers without affecting the rest of them.
For example: we could just decide to change the application layer and (instead of using a RestController) use a CLI application
or change our current DB and use another one like PostgreSQL. Any of this changes would not affect the other layers and we just
need to implement our new component and switch it with the old one.

The domain is responsible for implementing the business logic. In this case, as it is a demo app with small funcionality, we do
not have too much structure on it for the aggregate root and value objects but it could be extended depending on the needs.

The application layer is the one using our domain but does not implement business logic.

In our case, the infrastructure layer contains the DB/persistence implementations for the domain and Bean initialization (used
for injecting our DomainPriceService because the domain should not reference any of our infrastructure layer objects).

At persistence level the name of the fields have been keep as they were defined but the name of some of them has been changed
in the rest of the layers to avoid confusion with the type of object:
- PRICE_LIST: modified to be called "Rate" and avoid confusion with storing a List<Something>.

Even if there is no need to have it with the current functionality, a Brand Entity has been also created to generate the base
table and FK from the Prices table. At the moment we are not using any Collection to store the OneToMany relationship because we
are not using it. In case of adding it the fetching strategy should be reviewed (probably with Lazy loading) if there is a
big volume of data in a "real" case scenario.

Performance note: the query that we are using at the moment in our repository for retrieving a Price (when there is more than one
row with different priorities) is using a Pageable to obtain only the first row. This produces internally a chain of queries that
could cause performance issues depending on the volume and distribution of data in a "real" scenario (for example: it needs another
query with a "count" for total number of pages). This is because JPA does not allow queries with FETCH clauses. If the performance
becomes an issue we could use native queries (involves possible additional work for parsing the results) and use something like:

`SELECT * FROM PRICES P WHERE E.brandID=?1 AND E.productID=?2 AND ?3 BETWEEN P.START_DATE AND P.END_DATE ORDER BY P.PRIORITY DESC FETCH FIRST 1 ROWS ONLY`

A DB initialization script has been used to populate values when starting the application. Details can be found in the project dir
at /src/main/resources/data.sql file.

At CI level a job step has been created on GitHub to build/test the project on each push to the git repository. Details of the step
can be found in the project dir at /.github/workflows/actions.yml file.

## Setup

Clone the repository to your local workspace.
The repository contains a pom.xml to be able to download all the dependencies and build the project with Maven. 
Note that Java 17 is required for using the project.
You can perform this steps directly using the command line or using the options from your preferred IDE.

Example using command line:

- Open a Terminal window and go to your local workspace folder were the pom.xml file is located:

`cd myWorkspaceFolder`

- Execute Maven to download dependencies and build project:

`mvn package`

- Run the generated project JAR inside the target folder:

`cd target`
`java -jar price-microservice.jar`

## Usage

The project provides a API definition and can be accesed directly in your browser once the project JAR is up and running. 

- API definition (RAW):

http://localhost:8080/api-docs

- Swagger UI (Interface with the definition and functionality to be able to use the API directly in the browser):

http://localhost:8080/swagger-ui/index.html

If you prefer it you can also make direct calls using your terminal:

- Example call using curl: 

`curl -X "GET" "http://localhost:8080/price/1/35455/2020-06-16T21-00-00" -H "accept: application/json"`

## Room for Improvement

The project can be extended with much more functionality. For example:

- Add methods to check prices in different ways (All, paginated, for a product only,...)
- Extend the domain to add/update/delete prices
- Prepare the project for usage and integration with other DB types (For example: using TestContainers)

## Project Status

Project is: _complete_
