Pet Management App
==================

About
-----
This App allows you to create, search, delete and search information about pets that you want to store

This version allows you to interact through command line app only.


Build and Deploy Instructions
-----------------------------
To build sources locally follow these instructions and execute them from project base directory.

### Build 
Compile the program using javac command:
 
    javac -d build\ src\core\*.java src\core\model\*.java

Create executable JAR file using jar command:

    jar cfm petManager.jar manifest.txt -C build core
    
Run the program using java command and sending the input file name as argument:
    
    java -jar petManager.jar inputFileName.csv
    
Example `inputFileName.csv`

        CAT,Fluffy,M,20131231-145934       
        DOG,Sid,M,20121231-145934
        CAT,Mishi,M,20181231-145934       


