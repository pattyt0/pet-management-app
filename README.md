Pet Management App
==================

About
-----
This App allows you to create, search, delete and search information about pets that you want to store

Build and Deploy Instructions
-----------------------------
To build sources locally follow these instructions and execute them from project base directory.

### Build 
Compile the program using javac command:
 
    javac -d build\ src\app\core\*.java src\app\core\model\*.java src\app\core\comparators\*.java src\app\core\fileIO\*.java src\app\core\utils\*.java src\app\client\*.java src\app\command\*.java src\app\invoker\*.java

Create executable JAR file using jar command:

    jar cfm petManager.jar manifest.txt -C build app
    
Run the program using java command and sending the input file name as argument:
    
    java -jar petManager.jar inputFileName.csv
    
Example `inputFileName.csv`

        CAT,Fluffy,M,20131231-145934       
        DOG,Sid,M,20121231-145934
        CAT,Mishi,M,20181231-145934
        
### About this Version

* Allows you to interact through command line app only. 
* Thinking about the data structure to handle Pets information in memory it was selected TreeMap:
    * There is a possibility to have duplicate data pets and TreeMap allows store duplicated values, consider in a future add the information about the pet owner.
    * Due to requirement of delete specific pet, it was implemented a unique id to locate specific pet.    
* It was implemented Comparator interface taken into account that we could add more kinds of sorting for pets information and those are singleton because they will not change and could be used in other parts of the application.
* It was implemented Command pattern due to the behavior of the app making it simple to execute a command from Client app.
* Add case insensitive search
*    


