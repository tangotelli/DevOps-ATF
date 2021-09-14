package es.upm.miw.iwvg_devops.user_fractions;

import org.apache.logging.log4j.LogManager;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Searches {

    public Stream<String> findUserFamilyNameByUserNameDistinct(String userName) {
        return new UserDatabase().findAll()
                .filter(user -> userName.equals(user.getName()))
                .map(User::getFamilyName)
                .distinct();
    }

    public Stream<Integer> findFractionNumeratorByUserFamilyName(String userFamilyName) {
        return new UserDatabase().findAll()
                .peek(x -> LogManager.getLogger(this.getClass()).info("before: " + x))
                .filter(user -> userFamilyName.equals(user.getFamilyName()))
                .peek(x -> LogManager.getLogger(this.getClass()).info("after: " + x))
                .flatMap(user -> user.getFractions().stream())
                .map(Fraction::getNumerator);
    }

    public Stream<String> findUserFamilyNameByFractionDenominator(int fractionDenominator) {
        return new UserDatabase().findAll()
                .filter(user -> user.getFractions().stream()
                        .anyMatch(fraction -> fractionDenominator == fraction.getDenominator()))
                .map(User::getFamilyName);
    }

    public Stream<String> findUserFamilyNameInitialByAnyProperFraction() {
        return new UserDatabase().findAll()
                .filter(user -> user.getFractions().stream()
                        .anyMatch(fraction -> fraction.isProper()))
                .map(User::initials);
    }

    public Stream<String> findUserIdByAnyProperFraction() {
        return new UserDatabase().findAll()
                .filter(user -> user.getFractions().stream()
                        .anyMatch(fraction -> fraction.isProper()))
                .map(User::getId);
    }

    public Fraction findFractionMultiplicationByUserFamilyName(String familyName) {
        return new UserDatabase().findAll()
                .filter(user -> user.getFamilyName().equals(familyName))
                .flatMap(user -> user.getFractions().stream())
                .reduce(Fraction::multiply)
                .orElse(new Fraction(0, 0));
    }

    public Fraction findFirstFractionDivisionByUserId(String id) {
        return new UserDatabase().findAll()
                .filter(user -> user.getId().equals(id))
                .flatMap(user -> user.getFractions().stream())
                .limit(2)
                .reduce(Fraction::divide)
                .orElse(new Fraction(0, 0));
    }

    public Double findFirstDecimalFractionByUserName(String name) {
        return new UserDatabase().findAll()
                .filter(user -> user.getName().equals(name))
                .flatMap(user -> user.getFractions().stream())
                .collect(Collectors.toList())
                .get(0).decimal();
    }

    public Stream<String> findUserIdByAllProperFraction() {
        return new UserDatabase().findAll()
                .filter(user -> user.getFractions().stream()
                        .allMatch(fraction -> fraction.isProper()))
                .map(User::getId);
    }

    public Stream<Double> findDecimalImproperFractionByUserName(String name) {
        return new UserDatabase().findAll()
                .filter(user -> user.getName().equals(name))
                .flatMap(user -> user.getFractions().stream())
                .filter(fraction -> !fraction.isProper())
                .map(Fraction::decimal);
    }

    public Fraction findFirstProperFractionByUserId(String id) {
        return new UserDatabase().findAll()
                .filter(user -> user.getId().equals(id))
                .flatMap(user -> user.getFractions().stream())
                .filter(fraction -> fraction.isProper())
                .collect(Collectors.toList())
                .get(0);
    }

    public Stream<String> findUserFamilyNameByImproperFraction() {
        return new UserDatabase().findAll()
                .filter(user -> user.getFractions().stream()
                        .anyMatch(fraction -> !fraction.isProper()))
                .map(User::getFamilyName);
    }

    public Fraction findHighestFraction() {
        return new UserDatabase().findAll()
                .flatMap(user -> user.getFractions().stream())
                .max(new FractionComparator()).orElse(new Fraction(0,0));
    }

    public Stream<String> findUserNameByAnyImproperFraction() {
        return new UserDatabase().findAll()
                .filter(user -> user.getFractions().stream()
                        .anyMatch(fraction -> !fraction.isProper()))
                .map(User::getName);
    }

    public Stream<String> findUserFamilyNameByAllNegativeSignFractionDistinct() {
        return Stream.empty();
    }

    public Stream<Double> findDecimalFractionByUserName(String name) {
        return new UserDatabase().findAll()
                .filter(user -> user.getName().equals(name))
                .peek(x -> LogManager.getLogger(this.getClass()).info("usuario filtrado: " + x))
                .flatMap(user -> user.getFractions().stream())
                .peek(x -> LogManager.getLogger(this.getClass()).info("fracciones: " + x))
                .map(fraction -> fraction.decimal());
    }

    public Stream<Double> findDecimalFractionByNegativeSignFraction() {
        return Stream.empty();
    }

    public Fraction findFractionAdditionByUserId(String id) {
        return new UserDatabase().findAll()
                .filter(user -> user.getId().equals(id))
                .flatMap(user -> user.getFractions().stream())
                .reduce(Fraction::sum)
                .orElse(new Fraction(0, 0));
    }

    public Fraction findFirstFractionSubtractionByUserName(String name) {
        return new UserDatabase().findAll()
                .filter(user -> user.getName().equals(name))
                .flatMap(user -> user.getFractions().stream())
                .limit(2)
                .reduce(Fraction::subtract)
                .orElse(new Fraction(0, 0));
    }
}
