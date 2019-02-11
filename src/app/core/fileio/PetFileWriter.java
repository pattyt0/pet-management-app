package app.core.fileio;

import app.core.model.Pet;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class PetFileWriter {

    public static final String DB_FILE = "src/resources/db.txt";
    private Collection<Pet> pets;

    public PetFileWriter(Collection<Pet> pets) {
        this.pets = pets;
    }

    /**
     * TODO: replace System.err with a logger
     */
    public void savePets() {
        if(this.pets != null) {
            try ( FileWriter writer = new FileWriter(DB_FILE) ) {
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
}
