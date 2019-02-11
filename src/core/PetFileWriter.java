package core;

import core.model.Pet;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PetFileWriter {

    private static final String DB_FILE = "src/resources/db.txt";
    private List<Pet> pets;

    public PetFileWriter(List<Pet> pets) {
        this.pets = pets;
    }


    /**
     * TODO: replace System.err with a logger
     */
    public void savePets() {
        try ( FileWriter writer = new FileWriter(DB_FILE)) {
            for (Pet pet : pets) {
                writer.write(pet.getStoringDetails() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Can not write a file");
        }
    }
}
