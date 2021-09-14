package es.upm.miw.iwvg_devops.user_fractions;

import java.util.Comparator;

public class FractionComparator implements Comparator<Fraction> {

    @Override
    public int compare(Fraction o1, Fraction o2) {
        return o1.compareTo(o2);
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
