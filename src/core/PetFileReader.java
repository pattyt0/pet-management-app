package core;

import core.model.Pet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    private String inputFile;

    public PetFileReader(String filename)
    {
        inputFile = filename;
    }

    /**
     * Reads pets in CSV format from the given file.
     *
     * @return A list of Pets.
     */
    public List<Pet> getPets()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        List<Pet> pets;

        Function<String, Pet> createPet =
                record -> {
                    String petType;
                    String petName;
                    String petGender;
                    LocalDateTime lastUpdateDate;

                    String[] parts = record.split(",");
                    if(parts.length == NUMBER_OF_COLUMNS) {
                        try {
                            petType = parts[PET_TYPE].trim().replaceAll(SPECIAL_CHARACTERS_PATTERN,"");
                            petName = parts[PET_NAME].trim().replaceAll(SPECIAL_CHARACTERS_PATTERN,"");
                            petGender = parts[PET_GENDER].trim().replaceAll(SPECIAL_CHARACTERS_PATTERN,"");
                            lastUpdateDate = LocalDateTime.parse(parts[LAST_UPDATE_DATE].trim(), formatter);
                            return new Pet(petType, petName, petGender, lastUpdateDate);
                        }
                        catch(NumberFormatException e) {
                            System.out.println("Pet record has a malformed data: " + record);
                            return null;
                        }
                    }
                    else {
                        System.out.println("Pet record with wrong number of columns");
                        return null;
                    }
                };
        try ( Stream<String> lines = Files.lines(Paths.get(inputFile))){
            pets = lines.map(createPet)
                    .filter(petLine -> { return petLine != null;})
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        catch(IOException e) {
            System.out.println("Unable to open file " + inputFile);
            pets = null;
        }
        return pets;
    }
}
