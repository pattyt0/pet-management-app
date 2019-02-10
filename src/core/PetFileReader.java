package core;

import core.model.Pet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PetFileReader {
    private static final String SPECIAL_CHARACTERS_PATTERN = "[^a-zA-Z0-9]";
    private static final int NUMBER_OF_COLUMNS = 4;
    private static final int PET_TYPE = 0;
    private static final int PET_NAME = 1;
    private static final int PET_GENDER = 2;
    private static final int LAST_UPDATE_DATE = 3;

    public PetFileReader()
    {
        //not implemented
    }

    /**
     * Reads pets in CSV format from the given file.
     *
     * @param filename The file to be read with CSV format.
     * @return A list of Pets.
     */
    public List<Pet> getPets(String filename)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        Function<String, Pet> createPet =
                record -> {
                    String[] parts = record.split(",");
                    if(parts.length == NUMBER_OF_COLUMNS) {
                        try {
                            String petType = parts[PET_TYPE].trim().replaceAll(SPECIAL_CHARACTERS_PATTERN,"");
                            String petName = parts[PET_NAME].trim().replaceAll(SPECIAL_CHARACTERS_PATTERN,"");
                            String petGender = parts[PET_GENDER].trim().replaceAll(SPECIAL_CHARACTERS_PATTERN,"");
                            LocalDateTime lastUpdateDate = LocalDateTime.parse(parts[LAST_UPDATE_DATE].trim(), formatter);
                            return new Pet(petType, petName, petGender, lastUpdateDate);
                        }
                        catch(NumberFormatException e) {
                            System.out.println("Pet record has a malformed data: " + record);
                            return null;
                        }
                    }
                    else {
                        System.out.println("Pet record wrong number of columns: " + record);
                        return null;
                    }
                };
        ArrayList<Pet> pets;
        try ( Stream<String> lines = Files.lines(Paths.get(filename))){
            pets = lines.map(createPet)
                    .filter(petLine -> { return petLine != null;})
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        catch(IOException e) {
            System.out.println("Unable to open file" + filename + " Why: "+ e);
            pets = new ArrayList<>();
        }
        return pets;
    }
}
