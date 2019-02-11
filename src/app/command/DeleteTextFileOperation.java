package app.command;

import app.core.PetManager;

public class DeleteTextFileOperation implements TextFileOperation{
    public static final String CODEKEY = "code=";
    private String inputFile;
    private String code;

    public DeleteTextFileOperation(String filename, String code) {
        this.inputFile = filename;
        this.code = cleanParameter(code);
    }

    private String cleanParameter(String code) {
        return code.replace(CODEKEY, "");
    }

    @Override
    public void execute() {
        PetManager monitor=new PetManager();
        monitor.loadStoredPets();
        monitor.initialize();
        monitor.readPetsFromFile(inputFile);
        monitor.storePetsFromCache();

        monitor.deletePet(code);
        monitor.updateStoredPets();
        monitor.printPets();
    }
}
