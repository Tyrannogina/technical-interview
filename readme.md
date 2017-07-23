# Learning Grails and Groovy #
https://github.com/tyrannogina/technical-interview

### How to use ###
* Step 1: Install Grails. https://grails.org/download.html I recommend the SDKMAN installation if you are on a unix system.
* Step 2: Clone the repo into a folder or download the zip and unzip it in a folder.
* Step 3: In the terminal, go to this folder.
* Step 4: From that folder, run `grails run-app` command to have it running.
* Step 5: In your browser, go to [http://localhost:8080](http://localhost:8080) and voilà! There it is :)

## Goals ##
1. Create a program, using your favorite scripting language (Bash, Perl, Python etc.),  that takes as an input 2 integer numbers. The program does 2 things:
    * sums up these 2 numbers (i.e. 2+3 = 5 ) and writes the result into a file. 
    * uses the result of the sum ( in this example the 5) to randomly generate 100 random numbers, which should be saved in  another file (bonus, in a database). 
2. Create the full-stack using Groovy on Grails that will be calling the above program on the backend and on the frontend will be having a simple page that will be asking the user to give 2 numbers (so 2 boxes to write the numbers) and a button to press to execute the program. 
3. Once the program is executed the result of the sum of the numbers should be outputted (meaning the 5 in this example) and a normal distribution graph  of the  random numbers. 
For the graph any of the javascript libraries for plotting numbers can and should be used, and the random numbers should be retrieved from the database that they were saved after the execution of the program.

### Achieved ###
1. Created the whole program in Groovy. It takes as an input 2 integer numbers. 
    * Added a service class that does the calculations: sums the numbers and generates 100 random numbers using the sum as a seed.
2. Full-stack application in Grails created from scratch: the user inputs 2 numbers, a command class validates the input. In case there is no error, the application redirects you to the graph page, sending the random numbers list. 
3. A javascript library added through the resources then displays the random numbers.
* Implement a database in MongoDB and not crashing the build.

### Not achieved yet ### 
1. Writing data and reading data from files.
2. Writing data into and from the database. I managed briefly using the h2 temporary database that comes with Grails, but as soon as I managed to add a MongoDB database, I've been getting errors and still have not managed to make it work.
* Make the random numbers into a normal distribution so the graph shows that and not a line graph.
* Use the validation class from the /src folder as the best practices mandate, instead of having it in the controller.

## Things I've learned ##
* How the information flow works in Grails and how it should be distributed in views, controllers, models and services.
* How to do proper validation of form fields
* A LOT of Groovy. Probably I could code only in Groovy if enough time is provided, until I am more fluent at it.
* Understand routing in Grails: how to retrieve information from a form, how to send information and use it on the views.
* How to use .gsp files, how to add assets, how to add external resources (although I'm not using any right now), how to work with data sent from the controllers.
* The basics of GORM and configuration of databases in Hibernate, MySQL and Mongo.
* How Grails separates the model (as in theoretical the theoretical concept of MVC) into models that represent database objects to be mapped and Services, where all the logic of the program should be implemented.

## Issues I've had ##
* Setting up the environment for JVM in Linux is a pain and a torture I would only wish to my worst enemies.
* Setting up Groovy, Grails and Intellij IDEA (first I had the Community version, but that one does not have Grails support, so I switched to be able to follow some tutorials that used Intellij IDEA). I had a lot of non-programming issues.
* Fighting a lot with Grails strict structure. I use to program in Javascript/php and in much more flexible environments, so I had trouble adjusting to this.
* Bad documentation/examples: the documentation is very atomized, so it has taken me a lot of time to figure how Grails works in a Global scope.
* Old documentation/information: I have installed Grails 3.3, the last version, but most of the information found online does not apply to this version, and there is little information about 3.3 from scratch, most of it is only difference with previous versions, and not knowing previous versions I had to reasearch A LOT.

### Shortcuts I've taken and how would I implement them properly ###
* I have added all the javascript logic for the graph in the graph.gsp file. The good way to do it would be having most of it as functions in an external js file and just calling one function passing the data on the .gps file.
* The command class should not be in the same file as the Controller.
* I wouldn't use Grails styles, and definitely I will put a lot more effort in this terrible frontend.
* I would implement testing.
* I would implement error control and Model validation.
* The service methods are a bit of a mess right now, I would probably rethink the structure.

## Time spent in the project (aproximation) ##
* Configuring the environment (JVM, Groovy, Grails, Intellij): ~8 hours.
* Learning Groovy (tutorials and exercises): ~10 hours.
* Learning Grails (theory): ~3 hours.
* Fighting with Grails and MongoDB: ~18 hours.
* Project logic: ~2 hours
* Javascript libraries and display of graph: ~4 hours.

Approximate total: 45 hours.