package app.core.model;

import app.core.fileio.PetFileReader;
import app.core.utils.PetUtils;

import java.time.LocalDateTime;

public class Pet implements Comparable<Pet> {
    private String code;
    private String type;
    private String name;
    private String gender;
    private LocalDateTime lastUpdateDate;

    public Pet(String petCode, String petType, String petName, String petGender, LocalDateTime petLastUpdateDate) {
        code = petCode;
        type = petType;
        name = petName;
        gender = petGender;
        lastUpdateDate = petLastUpdateDate;
    }

    public String getStoringDetails() {
        return  type +
                ", " + name +
                ", " + gender +
                ", " + lastUpdateDate.format(PetFileReader.formatter) +
                ", " + code;
    }

    public String getDetails() {
        return code +
                ", type = " + type +
                ", name = " + name +
                ", gender = " + gender +
                ", lastUpdateDate = " + lastUpdateDate;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Override
    public int compareTo(Pet otherPet) {
        Long code1 = PetUtils.extractNumber(code);
        Long code2 = PetUtils.extractNumber(otherPet.getCode());
        int comparison = code1.compareTo(code2);
        if(comparison != 0){
            return comparison;
        }
        comparison = name.compareTo(otherPet.getName());
        if(comparison != 0){
            return comparison;
        }
        return type.compareTo(otherPet.getType());
    }

}
