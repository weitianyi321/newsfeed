# newsfeed
News Feed Application (Coding Challenge)

# 1.Front end
Used HTML and Bootstrap for front end rendering, and AngularJS for scripting. User uses JSON to communicate with Tomcat server, which uses Java Servlet for server side language.

# 2.Server
The server uses Java to handle GET and POST request. When receives get request, it renders a Gson jsonified string as response back to the webpage. When receives a post request, it parses the JSON string into SQL querying

# 3. Database
Uses MySQL database. The structure is as follow:
Database: newsfeed
Table: 
News (ID int NOT NULL AUTO_INCREMENT,     
Content varchar(255) NOT NULL,     
Date date,     
PRIMARY KEY (ID) );
