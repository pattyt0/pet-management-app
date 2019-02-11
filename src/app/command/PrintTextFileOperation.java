package app.command;

import app.core.PetManager;

public class PrintTextFileOperation implements TextFileOperation{
    @Override
    public void execute() {
        PetManager monitor = new PetManager();
        monitor.loadStoredPets();

        monitor.printPets();
    }
}
