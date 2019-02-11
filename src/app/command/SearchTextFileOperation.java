package app.command;

public class SearchTextFileOperation implements TextFileOperation{

    private String inputFile;
    private String firstParameter;
    private String secondParameter;

    /**
     * Constructor for one search parameter
     *
     * @param filename
     * @param searchParam1 could be name or type
     */
    public SearchTextFileOperation(String filename, String searchParam1) {
        this.inputFile = filename;
        this.firstParameter = searchParam1;
    }

    /**
     *
     * @param filename
     * @param searchParam1 could be gender or type
     * @param searchParam2 could be gender or type
     */
    public SearchTextFileOperation(String filename, String searchParam1, String searchParam2) {
        this.inputFile = filename;
        this.firstParameter = searchParam1;
        this.secondParameter = searchParam2;
    }

    @Override
    public void execute() {
//        PetManager monitor=new PetManager();
//        monitor.loadStoredPets();
//        monitor.initialize();
//        monitor.readPetsFromFile(inputFile);
//        monitor.storePetsFromCache();
//
//        System.out.println("Search by name :");
//        List<Pet> resultsByName=monitor.searchPetByName(name);
//        PetManager.sortByName(resultsByName);
//        monitor.printPets(resultsByName);
//
//        System.out.println("Search by type :");
//        Stringtype="CAT";
//        List<Pet>resultsByType=monitor.searchPetByType(type);
//        PetManager.sortByLastUpdateDate(resultsByType);
//        monitor.printPets(resultsByType);
//
//        System.out.println("Search by gender and type :");
//        Stringgender="F";
//        List<Pet>resultsByGenderAndType=monitor.searchPetByGenderAndType(gender,type);
//        PetManager.sortByLastUpdateDate(resultsByGenderAndType);
//        monitor.printPets(resultsByGenderAndType);

    }
}
