package core;

import core.model.Pet;

import java.util.ArrayList;
import java.util.List;


public class PetMonitor {
    private List<Pet> pets;

    /**
     * Create an PetMonitor.
     */
    public PetMonitor()
    {
        this.pets = new ArrayList<>();
    }

    /**
     * Add the pets recorded in the given filename to the current list.
     * @param filename A CSV file of Pet records.
     */
    public void addPets(String filename)
    {
        PetFileReader reader = new PetFileReader();
        pets.addAll(reader.getPets(filename));
    }

    /**
     * Print details of all the pets.
     */
    public void printList()
    {
        pets.forEach(pet -> System.out.println(pet.getDetails()));
    }

    /**
     * Print details of all the pets of the given type.
     * @param petType The type of animal.
     */
    public void printPetsOf(String petType)
    {
        pets.stream()
                .filter(pet -> petType.equals(pet.getType()))
                .forEach(pet -> System.out.println(pet.getDetails()));
    }

    /**
     * Print all the pets by the given name.
     * @param petName The ID of the name.
     */
    public void printPetsBy(String petName)
    {
        pets.stream()
                .filter(pet -> pet.getName().equals(petName))
                .map(pet -> {
                    return pet.getDetails();
                })
                .forEach(details -> System.out.println(details));
    }

    /**
     * TODO: Add custom exception handler and add a logger
     *
     * @param args input file which contains pets information
     */
    public static void main(String[] args){
        try{
            if(args.length > 0) {
                PetMonitor monitor = new PetMonitor();
                monitor.addPets(args[0]);
                monitor.printList();
            }else{
                throw new Exception();
            }
        }
        catch(Exception e){
            System.out.println("Please add input file to command line:");
            System.out.println("e.g. java -jar petManager.jar PetsInformation.csv");
        }
    }
}
