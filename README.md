# band tracker



#### By _**Paul Hess**_

## Description

a practice in java

## Setup/Installation Requirements

* _Clone this repository_
* _Install the [Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Java SRE](http://www.java.com/en/)._
* _[Install gradle](http://codetutr.com/2013/03/23/how-to-install-gradle/)_
* _Open a terminal and run Postgres_
```
$ postgres
```
* _Open a new tab in terminal and create the `band_tracker` database:_
```
$ psql
$ CREATE DATABASE band_tracker;
$ psql band_tracker < bands_venues.sql
```
Manual database setup:
CREATE TABLE bands (id serial PRIMARY KEY, band_name varchar NOT NULL, band_genre varchar);
CREATE TABLE venues (id serial PRIMARY KEY, venue_name varchar NOT NULL, location varchar);
CREATE TABLE bands_venues (id serial PRIMARY KEY, band_id NOT NULL, venue_id NOT NULL);

for tests to pass CREATE DATABASE band_tracker_test WITH TEMPLATE band_tracker;

* _Navigate back to the directory where this repository has been cloned and run gradle:_
```
$ gradle run
```
* _Open localhost:4567 in a browser._

## Known Bugs

_No current known bugs._

## Support and contact details

_To contact, leave a comment on Github._

## Technologies Used

* _Java_
* _JUnit_
* _FluentLenium_
* _Gradle_
* _Spark_
* _SQL_

### License

*MIT License*

Copyright (c) 2016 **_Paul Hess_**
