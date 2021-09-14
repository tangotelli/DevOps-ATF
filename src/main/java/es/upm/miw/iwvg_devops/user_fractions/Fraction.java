package es.upm.miw.iwvg_devops.user_fractions;

import java.util.Objects;

/**
 * Conceptos: Las fracciones propias son aquellas cuyo numerador es menor que el denominador
 * <p>
 * Las fracciones impropias son aquellas cuyo numerador es mayor que el denominador
 * <p>
 * Dos fracciones son equivalentes cuando el producto de extremos (numerador de la primera por denominador de la segunda) es igual al
 * producto de medios (denominador de la primera por el numerador de la segunda)
 * <p>
 * Las fracciones irreducibles son aquellas que no se pueden simplificar, esto sucede cuando el numerador y el denominador son primos entre
 * sí
 * <p>
 * Reducir varias fracciones a común denominador consiste en convertirlas en otras equivalentes que tengan el mismo denominador
 * <p>
 * Comparar fracciones
 * <p>
 * Suma fracciones: En primer lugar se reducen los denominadores a común denominador, y se suman o se restan los numeradores de las
 * fracciones equivalentes obtenidas
 * <p>
 * Multiplicación: La multiplicación de dos fracciones es otra fracción que tiene: Por numerador el producto de los numeradores. Por
 * denominador el producto de los denominadores.
 * <p>
 * La división de dos fracciones es otra fracción que tiene: Por numerador el producto de los extremos. Por denominador el producto de los
 * medios. Invertir fraccion
 */
public class Fraction implements Comparable<Fraction> {

    private int numerator;

    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction() {
        this(1, 1);
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public double decimal() {
        return (double) numerator / denominator;
    }

    @Override
    public String toString() {
        return "Fraction{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }

    public boolean isProper() {
        return this.numerator < this.denominator;
    }

    public boolean isEquivalentTo(Fraction fraction) {
        return this.numerator * fraction.getDenominator() == this.denominator * fraction.getNumerator();
    }

    public void changeDenominator(int newDenominator) {
        this.setNumerator((newDenominator / this.denominator) * this.numerator);
        this.setDenominator(newDenominator);
    }

    public Fraction sum(Fraction fraction) {
        if (this.denominator != fraction.getDenominator()) {
            this.reduceToCommonDenominator(fraction);
        }
        return new Fraction(this.numerator + fraction.getDenominator(), this.denominator);
    }

    public Fraction subtract(Fraction fraction) {
        if (this.denominator != fraction.getDenominator()) {
            this.reduceToCommonDenominator(fraction);
        }
        return new Fraction(this.numerator - fraction.getDenominator(), this.denominator);
    }

    public void reduceToCommonDenominator(Fraction fraction) {
        int leastCommonMultiple = this.leastCommonMultiple(this.denominator, fraction.getDenominator());
        this.changeDenominator(leastCommonMultiple);
        fraction.changeDenominator(leastCommonMultiple);
    }

    private int leastCommonMultiple(int a, int b) {
        return (a / this.greatestCommonDivisor(a, b)) * b;
    }

    private int greatestCommonDivisor(int a, int b) {
        if (a == 0)
            return b;
        return greatestCommonDivisor(b % a, a);
    }

    public Fraction multiply(Fraction fraction) {
        return new Fraction(this.numerator * fraction.getNumerator(), this.denominator * fraction.getDenominator());
    }

    public Fraction divide(Fraction fraction) {
        return new Fraction(this.numerator * fraction.getDenominator(), this.denominator * fraction.getNumerator());
    }

    @Override
    public int compareTo(Fraction o) {
        if (this.decimal() < o.decimal()) {
            return -1;
        } else if (this.decimal() == o.decimal()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}
