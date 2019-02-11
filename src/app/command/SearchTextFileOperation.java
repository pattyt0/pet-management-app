package app.command;

import app.core.PetManager;
import app.core.model.Pet;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SearchTextFileOperation implements TextFileOperation{

    public static final String NAMEKEY = "name=";
    public static final String TYPEKEY = "type=";
    public static final String GENDERKEY = "gender=";

    private String inputFile;
    private Pair<String, String> firstParameter;
    private Pair<String, String> secondParameter;

    /**
     * Constructor for one search parameter
     *
     * @param filename
     * @param searchParam1 could be name or type
     */
    public SearchTextFileOperation(String filename, String searchParam1) {
        this.inputFile = filename;
        if(searchParam1.contains(NAMEKEY))
            this.firstParameter = new Pair<>(NAMEKEY, cleanParameter(searchParam1, NAMEKEY));
        if(searchParam1.contains(TYPEKEY))
            this.firstParameter = new Pair<>(TYPEKEY, cleanParameter(searchParam1, TYPEKEY));
    }

    /**
     *
     * @param filename
     * @param searchParam1 could be gender or type
     * @param searchParam2 could be gender or type
     */
    public SearchTextFileOperation(String filename, String searchParam1, String searchParam2) {
        this.inputFile = filename;
        if(searchParam1.contains(GENDERKEY) && searchParam2.contains(TYPEKEY)) {
            this.firstParameter = new Pair<>(GENDERKEY, cleanParameter(searchParam1, GENDERKEY));
            this.secondParameter = new Pair<>(TYPEKEY, cleanParameter(searchParam2, TYPEKEY));
        }
        if(searchParam1.contains(TYPEKEY) && searchParam2.contains(GENDERKEY)){
            this.firstParameter = new Pair<>(TYPEKEY, cleanParameter(searchParam1, TYPEKEY));
            this.secondParameter = new Pair<>(GENDERKEY, cleanParameter(searchParam2, GENDERKEY));
        }
    }

    private String cleanParameter(String searchParam1, String parameterKey) {
        return searchParam1.replace(parameterKey, "");
    }

    @Override
    public void execute() {
        PetManager monitor=new PetManager();
        monitor.loadStoredPets();
        monitor.initialize();

        monitor.readPetsFromFile(inputFile);
        monitor.storePetsFromCache();

        if(firstParameter != null && secondParameter == null){
            if(firstParameter.getKey().equals(NAMEKEY)){
                System.out.println("Search by name :");
                List<Pet> resultsByName = monitor.searchPetByName(firstParameter.getValue());
                PetManager.sortByName(resultsByName);
                monitor.printPets(resultsByName);
            }
            if(firstParameter.getKey().equals(TYPEKEY)){
                System.out.println("Search by type :");
                List<Pet>resultsByType = monitor.searchPetByType(firstParameter.getValue());
                PetManager.sortByLastUpdateDate(resultsByType);
                monitor.printPets(resultsByType);
            }
        }
        if(firstParameter != null && secondParameter != null){
            List<Pet>resultsByGenderAndType = new ArrayList<>();

            System.out.println("Search by gender and type :");
            if(firstParameter.getKey().equals(GENDERKEY) && secondParameter.getKey().equals(TYPEKEY)){
                resultsByGenderAndType=monitor.searchPetByGenderAndType(firstParameter.getValue(),secondParameter.getValue());
            }
            if(firstParameter.getKey().equals(TYPEKEY) && secondParameter.getKey().equals(GENDERKEY)){
                resultsByGenderAndType=monitor.searchPetByGenderAndType(secondParameter.getValue(),firstParameter.getValue());
            }
            PetManager.sortByLastUpdateDate(resultsByGenderAndType);
            monitor.printPets(resultsByGenderAndType);
        }
    }
}
