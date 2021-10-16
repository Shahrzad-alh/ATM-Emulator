# ATM-Emulator
ATM is a part of our life activity, which helps us in day transactions and business. 
An automated teller machine (ATM) is a computerized telecommunications instrument that provides the clients of a financial institution with access to financial transactions in a public space without the need for a cashier, human clerk or bank teller.  At this time, the ATM provides the people good services especially the people can get money at any time. We need the ATM system because not all the bank branches are open all days of the week, and some of the customers may not in a situation, they can visit the bank every time, and they want to withdraw money or deposit money for emergency cases.


The ATM Emulator is a web app created using Spring Boot and various Java plugins listed as dependencies in the Maven POM file using JavaScript, HTML, and CSS in the front-end 
and MySQL database 

How to Run:

Install Java 8, MySQL 5.1 or above and a proper IDE for Java and MySQL (I used IntelliJ IDEA and Navicat).
Configure MySQL with username `root` and password `Admin123`. 
Chech the `src/main/resources/application.properties` if you prefer other credentials.
A SQL script file named `atm.sql` located in root directory. Run the script creates a database called `atm` with tables `users` and `accounts` filled with information.
In your IDE, build the project with Maven 4.0, and run 'Application.java`. 

Use Dycrypted PIN numbers as password! Dycrypted PIN numbers are located in atm.sql (commented after each row)
