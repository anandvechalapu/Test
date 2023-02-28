
Solution:
The following Java classes can be used to create a Spring Boot application that meets the acceptance criteria of the user story:

Controller:
The controller class will be responsible for handling the incoming requests from the user and returning the requested code requests with their status and GitHub link. The controller will also handle the configuration of the screen refresh.

Service:
The service class will be responsible for fetching the requests information from the Java API and the database. It will also contain the logic for displaying the requested code requests with their status and GitHub link on the History screen.

Repository:
The repository class will be responsible for connecting to the database to fetch the requests information. It will also contain the logic for updating the requests information in the database.