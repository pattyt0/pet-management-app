package app.core;

import app.core.model.Pet;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PetManagerTest {

    private PetManager petManager;
    private String testFilename;
    public PetManagerTest() {
    }

    @Before
    public void setUp(){
        petManager = new PetManager();
        testFilename =  "test\\app\\resources\\testPets.csv";
    }

    @Test
    public void ValidateReadPetsFromFileAreStored() {
        petManager.initialize();
        petManager.readPetsFromFile(testFilename);

        assertEquals(5, petManager.getTotalPets());
    }

    @Test
    public void DeleteAnExistingPet() {
        petManager.initialize();
        petManager.readPetsFromFile(testFilename);
        List<Pet> pets = petManager.searchPetByName("Dalila");
        petManager.deletePet(pets.get(0).getCode());

        List<Pet> result = petManager.searchPetByName("Dalila");
        assertEquals(0, pets.size());
        assertEquals(0, result.size());
    }

    @Test
    public void SearchAnExistingPetByName() {
        String catName = "Mishi";
        petManager.initialize();
        petManager.readPetsFromFile(testFilename);
        List<Pet> result = petManager.searchPetByName(catName);

        assertEquals(1, result.size());
        assertEquals(catName, result.get(0).getName());
    }

    @Test
    public void SearchAnExistingPetByType() {
        String petType = "cat";
        petManager.initialize();
        petManager.readPetsFromFile(testFilename);
        List<Pet> result = petManager.searchPetByType(petType);

        assertFalse(result.isEmpty());
        assertEquals(petType, result.get(0).getType().toLowerCase());
    }

    @Test
    public void SearchAnExistingPetByGenderAndType() {
        String petType = "cat";
        String petGender = "f";
        petManager.initialize();
        petManager.readPetsFromFile(testFilename);
        List<Pet> result = petManager.searchPetByGenderAndType(petGender, petType);

        assertFalse(result.isEmpty());
        assertEquals(petGender, result.get(0).getGender().toLowerCase());
        assertEquals(petType, result.get(0).getType().toLowerCase());
    }

}
