Demo
====

The goal of this project is to demostrate my coding skills on a quite simple example.
This application can read a list of product items that matches some search criteria from Zalando site and save them to some storage.
Also there is a possibility to read previously stored data for further analisys (e.g. monitoring the price fluctuation for some products).

Usage example:
- reads data about "snowboots" from zalando.be portal and saves these data to "sboots.csv" file
  mvn clean compile exec:java -Dwebhost=http://www.zalando.be/ -Dstorage.label=sboots.csv -Dsection=snowboots

- reads previously saved data from "sboots.csv"
  mvn clean compile exec:java -Dmode=read -Dstorage.label=sboots.csv

- default parameters - reads data about maksi skirts from Britain Zalando and saves to "output.csv" file
  mvn clean compile exec:java from 

- default parameters - reads saved data "output.csv" file
  mvn clean compile exec:java -Dmode=read