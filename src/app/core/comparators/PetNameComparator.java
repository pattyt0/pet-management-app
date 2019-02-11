package app.core.comparators;

import app.core.model.Pet;

import java.util.Comparator;

public class PetNameComparator implements Comparator<Pet> {
    private static final PetNameComparator instance = new PetNameComparator();

    public static PetNameComparator getInstance() {
        return instance;
    }

    private PetNameComparator() {
        super();
    }

    @Override
    public int compare(Pet p1, Pet p2){
        return p1.getName().compareTo(p2.getName());
    }
}
