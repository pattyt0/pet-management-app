package app.client;

import app.command.DeleteTextFileOperation;
import app.command.ReadTextFileOperation;
import app.command.SearchTextFileOperation;
import app.invoker.TextFileOperationExecutor;

public class PetManagerClientApp {

    /**
     * TODO: Add custom exception handler and add a logger
     *
     * @param args input file which contains pets information
     */
    public static void main(String[] args) {
        TextFileOperationExecutor textFileOperationExecutor = new TextFileOperationExecutor();

        if(args.length == 1 && args[0].contains(".csv")){
            textFileOperationExecutor.executeOperation(new ReadTextFileOperation(args[0]));
        }
        if(args.length > 1 && args[0].contains(".csv")) {
            if(args.length == 2 && args[1].contains("code")){
                textFileOperationExecutor.executeOperation(new DeleteTextFileOperation(args[0], args[1]));
            }
            if(args.length == 2 && args[1].contains("name")){
                textFileOperationExecutor.executeOperation(new SearchTextFileOperation(args[0], args[1]));
            }
            if(args.length == 2 && args[1].contains("type")){
                textFileOperationExecutor.executeOperation(new SearchTextFileOperation(args[0], args[1]));
            }
            if(args.length == 3 && args[1].contains("gender") && args[2].contains("type")){
                textFileOperationExecutor.executeOperation(new SearchTextFileOperation(args[0], args[1], args[2]));
            }
        }
        if(args.length < 1 && !args[0].contains(".csv")){
            System.out.println("Please add input file to command line e.g. java -jar petManager.jar PetsInformation.csv");
        }
    }
}
