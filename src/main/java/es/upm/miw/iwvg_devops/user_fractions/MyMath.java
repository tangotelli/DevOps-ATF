package es.upm.miw.iwvg_devops.user_fractions;

public class MyMath {

    public int leastCommonMultiple(int a, int b) {
        return (a / this.greatestCommonDivisor(a, b)) * b;
    }

    public int greatestCommonDivisor(int a, int b) {
        if (a == 0)
            return b;
        return greatestCommonDivisor(b % a, a);
    }
}
