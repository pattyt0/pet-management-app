package app.command;

import app.core.PetManager;

public class ReadTextFileOperation implements TextFileOperation{
    private String inputFile;
    public ReadTextFileOperation(String inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public void execute() {
        PetManager monitor = new PetManager();
        monitor.loadStoredPets();
        monitor.initialize();
        monitor.readPetsFromFile(inputFile);
        monitor.storePetsFromCache();

        monitor.printPets();
    }
}
