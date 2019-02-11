package core;

import core.comparators.PetLastUpdateDateComparator;
import core.comparators.PetNameComparator;
import core.model.Pet;

import java.util.*;

public class PetManager {
    private List<Pet> pets;

    /**
     * Create an PetManager.
     */
    public PetManager()
    {
        this.pets = new ArrayList<>();
    }

    /**
     * Add pets recorded in the given filename to the current list.
     * @param filename A CSV file of Pet records.
     */
    public void addPets(String filename)
    {
        PetFileReader reader = new PetFileReader(filename);
        pets.addAll(reader.getPets());
    }

    /**
     * Store pets recorded in the given filename to the current list.
     */
    private void storePets() {
        PetFileWriter writer = new PetFileWriter(pets);
        writer.savePets();
    }

    /**
     * Print details of all the pets.
     */
    public void printPets(){
        for(Pet petDetails : pets) {
            System.out.println(petDetails.getDetails());
        }

    }

    public List<Pet> getPets(){
        return this.pets;
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
        try{
            if(args.length > 0) {
                PetManager monitor = new PetManager();
                monitor.addPets(args[0]);
                monitor.storePets();
                monitor.printPets();
                System.out.println("Sort by name");
                PetManager.sortByName(monitor.getPets());
                monitor.printPets();
                System.out.println("Sort by last update date");
                PetManager.sortByLastUpdateDate(monitor.getPets());
                monitor.printPets();
            }else{
                throw new IllegalArgumentException();
            }
        }
        catch(Exception e){
            System.out.println("Please add input file to command line:");
            System.out.println("e.g. java -jar petManager.jar PetsInformation.csv");
        }
    }
}
