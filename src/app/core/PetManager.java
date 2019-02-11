package app.core;

import app.core.comparators.PetNameComparator;
import app.core.fileio.PetFileReader;
import app.core.fileio.PetFileWriter;
import app.core.fileio.PetReadType;
import app.core.model.Pet;
import app.core.utils.PetUtils;
import app.core.comparators.PetLastUpdateDateComparator;

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
    public void initialize() {
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
    public void storePetsFromCache() {
        PetFileWriter writer = new PetFileWriter(pets.values());
        writer.savePets();
    }

    public void loadStoredPets() {
        PetFileReader reader = new PetFileReader(PetFileWriter.DB_FILE, PetReadType.FROM_DB);
        List<Pet> readPets = reader.getPets();
        if(readPets != null) {
            for (Pet pet:readPets) {
                pets.put(pet.getCode(), pet);
            }
        }
    }


    public void deletePet(String code) {
        if(pets!=null && pets.containsKey(code)) {
            System.out.println("Deleting code "+code);
            Pet pet = pets.get(code);
            pets.remove(code, pet);
        }else{
            System.out.println("Code is not used "+code);
        }
    }

    public void updateStoredPets() {
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

    public void printPets(List<Pet> pets){
        for(Pet pet : pets) {
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

    public List<Pet> searchPetByName(String name) {
        List<Pet> result = new ArrayList<>();
        for(Pet petDetails:pets.values()) {
            if(petDetails.getName().equalsIgnoreCase(name))
                result.add(petDetails);
        }
        return result;
    }

    public List<Pet> searchPetByType(String type) {
        List<Pet> result = new ArrayList<>();
        for(Pet petDetails:pets.values()) {
            if(petDetails.getType().equalsIgnoreCase(type))
                result.add(petDetails);
        }
        return result;
    }

    public List<Pet> searchPetByGenderAndType(String gender, String type) {
        List<Pet> result = new ArrayList<>();
        for(Pet petDetails:pets.values()) {
            if(petDetails.getGender().equalsIgnoreCase(gender) && petDetails.getType().equalsIgnoreCase(type))
                result.add(petDetails);
        }
        return result;
    }

}
