package core;

import core.fileio.PetFileReader;
import core.fileio.PetFileWriter;
import core.comparators.PetLastUpdateDateComparator;
import core.comparators.PetNameComparator;
import core.fileio.PetReadType;
import core.model.Pet;
import core.utils.PetUtils;

import java.util.*;

public class PetManager {
    private TreeMap<String, Pet> pets;
    private Long currentSize;
    /**
     * Create an PetManager.
     */
    public PetManager()
    {
        this.currentSize = 0L;
        this.pets = new TreeMap<>();
    }

    /**
     * Initialize Pet information counter with pets size in file DB
     */
    private void initialize() {
        if(this.pets != null && this.pets.size()==0) {
            this.currentSize = Long.valueOf(this.pets.size());
        }else{
            this.currentSize = PetUtils.extractNumber(this.pets.lastKey()) + 1;
        }
    }

    /**
     * Add pets recorded in the given filename to the current list.
     * Don't persist data, it loads in the cache
     * @param filename A CSV file of Pet records.
     */
    public void readPetsFromFile(String filename)
    {
        PetFileReader reader = new PetFileReader(filename, currentSize, PetReadType.FROM_CSV);
        for (Pet pet:reader.getPets()) {
            pets.put(pet.getCode(), pet);
        }
    }

    /**
     * Store pets recorded in the given filename to the current list.
     */
    private void storePetsFromCache() {
        PetFileWriter writer = new PetFileWriter(pets.values());
        writer.savePets();
    }

    private void loadStoredPets() {
        PetFileReader reader = new PetFileReader(PetFileWriter.DB_FILE, PetReadType.FROM_DB);
        List<Pet> readPets = reader.getPets();
        if(readPets != null) {
            for (Pet pet:readPets) {
                pets.put(pet.getCode(), pet);
            }
        }
    }


    private void deletePet(String code) {
        if(pets!=null && pets.containsKey(code)) {
            System.out.println("deleting code "+code);
            Pet pet = pets.get(code);
            pets.remove(code, pet);
        }
    }

    private void updateStoredPets() {
        if(pets!=null) {
            PetFileWriter writer = new PetFileWriter(pets.values());
            writer.savePets();
        }
    }

    /**
     * Print details of all the pets.
     */
    public void printPets(){
        Set<Pet> sortedDetails = new TreeSet<>(pets.values());
        for(Pet pet : sortedDetails) {
            System.out.println(pet.getDetails());
        }
    }

    public List<Pet> getPets(){
        List<Pet> pets = new ArrayList<>(this.pets.values());
        return pets;
    }

    /**
     * Print details of all the pets alphabetically by name .
     * @param pets list of pets.
     */
    public static void sortByName(List<Pet> pets)
    {
        Collections.sort(pets, PetNameComparator.getInstance());
    }

    /**
     * Print details of all the pets alphabetically by name .
     * @param pets list of pets.
     */
    public static void sortByLastUpdateDate(List<Pet> pets)
    {
        Collections.sort(pets, PetLastUpdateDateComparator.getInstance());
    }

    /**
     * TODO: Add custom exception handler and add a logger
     *
     * @param args input file which contains pets information
     */
    public static void main(String[] args){
        if(args.length > 0) {
            PetManager monitor = new PetManager();
            monitor.loadStoredPets();
            monitor.initialize();
//            monitor.printPets();
            monitor.readPetsFromFile(args[0]);
            monitor.storePetsFromCache();
//                monitor.printPets();
//                System.out.println("Sort by last update date");
//                PetManager.sortByLastUpdateDate(monitor.getPets());
//                monitor.printPets();
//            System.out.println("Delete Pet0");
            monitor.deletePet("CATM0");
            monitor.updateStoredPets();

            System.out.println("Sort by name");
            PetManager.sortByName(monitor.getPets());

            monitor.printPets();
        }else{
            System.out.println("Please add input file to command line:");
            System.out.println("e.g. java -jar petManager.jar PetsInformation.csv");
        }
    }
}
