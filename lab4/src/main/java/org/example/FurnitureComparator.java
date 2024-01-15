package org.example;

import java.util.Comparator;

class FurnitureComparator implements Comparator<Furniture> {
    FurnitureComparator() {
    }

    public int compare(Furniture first, Furniture second) {
        if (first.getPrice() == second.getPrice()) {
            return 0;
        } else {
            return first.getPrice() > second.getPrice() ? 1 : -1;
        }
    }
}
