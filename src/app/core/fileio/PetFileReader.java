package app.core.fileio;

import app.core.model.Pet;
import app.core.utils.PetUtils;

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
    private static final int PET_TYPE = 0;
    private static final int PET_NAME = 1;
    private static final int PET_GENDER = 2;
    private static final int LAST_UPDATE_DATE = 3;
    private static final int PET_CODE = 4;
    private static int numberOfColumns;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    private PetReadType petReadType;
    private String inputFile;
    private Long internalCounter;


    public PetFileReader(String filename, PetReadType readType)
    {
        inputFile = filename;
        internalCounter = 0L;
        petReadType = readType;
        initializeColumnsNumber(readType);
    }

    public PetFileReader(String filename, Long currentSize, PetReadType readType) {
        inputFile = filename;
        internalCounter = currentSize;
        petReadType = readType;
        initializeColumnsNumber(readType);
    }

    private static void initializeColumnsNumber(PetReadType readType) {
        if (readType.equals(PetReadType.FROM_CSV)) {
            numberOfColumns = 4;
        }else{
            numberOfColumns = 5;
        }
    }

    /**
     * Reads pets in CSV format from the given file.
     *
     * @return A list of Pets.
     */
    public List<Pet> getPets()
    {
        List<Pet> pets;

        Function<String, Pet> createPet =
                record -> {
                    String petCode;
                    String petType;
                    String petName;
                    String petGender;
                    LocalDateTime lastUpdateDate;

                    String[] parts = record.split(",");
                    if(parts.length == numberOfColumns) {
                        try {
                            petType = parts[PET_TYPE].trim().replaceAll(SPECIAL_CHARACTERS_PATTERN,"");
                            petName = parts[PET_NAME].trim().replaceAll(SPECIAL_CHARACTERS_PATTERN,"");
                            petGender = parts[PET_GENDER].trim().replaceAll(SPECIAL_CHARACTERS_PATTERN,"");
                            lastUpdateDate = LocalDateTime.parse(parts[LAST_UPDATE_DATE].trim(), formatter);

                            if(petReadType.equals(PetReadType.FROM_DB)) {
                                petCode = parts[PET_CODE].trim().replaceAll(SPECIAL_CHARACTERS_PATTERN, "");

                            }else{
                                petCode = PetUtils.generateUniqueInternalCode(petType, petGender, internalCounter);
                                incrementCounter();
                            }
                            return new Pet(petCode, petType, petName, petGender, lastUpdateDate);
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

    private void incrementCounter() {
        this.internalCounter++;
    }
}
