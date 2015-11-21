Goal
====
Produce a simple web-app backend to complement the supplied front-end code.

Mandatory Work
--------------
Fork this repository. Starting with the provided HTML, CSS, and JS, create a Java-based REST API that:

1. Saves expenses as entered to a database.
2. Retrieves them for display on the page. 
3. Add a new column to the table displaying the VAT amount for each expense.
4. Alter the README to contain instructions on how to build and run your app.

VAT is the UK’s sales tax. It is 20% of the value of the expense, and is included in the amount entered by the user.

Give our account `alchemytec` access to your fork, and send us an email when you’re done. Feel free to ask questions if anything is unclear, confusing, or just plain missing.

Extra Credit
------------
Calculate the VAT client-side as the user enters a new expense, before they save the expense to the database.

Questions
---------
##### What frameworks can I use?
That’s entirely up to you, as long as they’re OSS. We’ll ask you to explain the choices you’ve made. Please pick something you're familiar with, as you'll need to be able to discuss it.

##### What application servers can I use?
Anyone you like, as long as it’s available OSS. You’ll have to justify your decision. We use dropwizard and Tomcat internally. Please pick something you're familiar with, as you'll need to be able to discuss it.

##### What database should I use?
MySQL or PostgreSQL. We use MySQL in-house.

##### What will you be grading me on?
Elegance, robustness, understanding of the technologies you use, tests, security. 

##### Will I have a chance to explain my choices?
Feel free to comment your code, or put explanations in a pull request within the repo. If we proceed to a phone interview, we’ll be asking questions about why you made the choices you made. 

##### Why doesn’t the test include X?
Good question. Feel free to tell us how to make the test better. Or, you know, fork it and improve it!


# The solution
For this test purpose I decide to reorganize a little bit the frontend structure in order to integrate it with the backend. It gives a better way to build and ship the software as one artifact.

The source code previously under the folder src are now on the src/main/web.

When running the build commands on the instructions below, the build tool will generate the static sources on the right folders.

### Technology choice:
I choose [springboot](http://projects.spring.io/spring-boot/) as the base framework because it brings me everything I need to develop the solution, with very little code and very few overhead. Also the [spring-web-actuator](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready) brings a lot facilities to monitor a web application.

The data access is done through [spring-data-jpa](http://projects.spring.io/spring-data-jpa/), it gives for free the CRUD, pagging and sorting functionalities.

The web server is an embedded tomcat, but spring supports (jetty)[https://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-servlet-containers.html#howto-use-jetty-instead-of-tomcat] and netty (with reactor) as well.

The build tools is (gradle)[http://gradle.org/], it provides me a nice integration with (node)[https://github.com/srs/gradle-node-plugin] and (gulp)[https://github.com/srs/gradle-gulp-plugin], the developer don't need to have any of this tools installed to start developing, it just need java 8+.

I decided to use groovy instead of java because it makes my code base really small, is much more straightforward with almost any boiler plate, and with the same performance.

There is no unit test in the application since there is no logic to justify the creation of it.

### How to build and run:

`gradlew run` will build the frontend application using gulp, will build the java application, put both together as one single jar and start the embedded webserver with the application. You need to have a mysql database running with the a database called expenses and a user/pass dbuser/dbpass, the DDL schema will be self generated by hibernate. The default database address is localhost, it can be changed in the file application.yml inside the application or set as an environmento variable DATABASE=... .

The application can be build using [docker](https://www.docker.com/), the Dockerfile has the instructions to build the application inside a container, configure the necessary paths and run the application.

`docker build -t expenses .` will generate a docker container named expenses.

`docker run -p 8080:8080 expenses` will run the application and expose the por 8080.

There is also a [docker-compose file](https://docs.docker.com/compose/) in order to provide the application database as well, so you don't need to worry about that.

`docker-compose build` will build the application container.

`docker-compose up` will start the application with the database and expose it on the port 8080.
