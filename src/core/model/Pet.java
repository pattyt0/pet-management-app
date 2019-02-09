package core.model;

import java.time.LocalDateTime;

public class Pet {
    private final String type;
    private final String name;
    private final String gender;
    private final LocalDateTime lastUpdateDate;

    public Pet(String petType, String petName, String petGender, LocalDateTime petLastUpdateDate) {
        type = petType;
        name = petName;
        gender = petGender;
        lastUpdateDate = petLastUpdateDate;
    }

    public String getDetails() {
        return type +
                ", name = " + name +
                ", gender = " + gender +
                ", lastUpdateDate = " + lastUpdateDate;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
