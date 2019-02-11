package app.core.comparators;

import app.core.model.Pet;

import java.util.Comparator;

public class PetLastUpdateDateComparator implements Comparator<Pet> {
    private static final PetLastUpdateDateComparator instance = new PetLastUpdateDateComparator();

    public static PetLastUpdateDateComparator getInstance() {
        return instance;
    }

    private PetLastUpdateDateComparator() {
        super();
    }

    @Override
    public int compare(Pet p1, Pet p2){
        return p2.getLastUpdateDate().compareTo(p1.getLastUpdateDate());
    }
}
