package es.upm.miw.iwvg_devops.user_fractions;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchesTest {

    @Test
    void testFindUserFamilyNameByUserNameDistinct() {
        assertEquals(List.of("Torres"), new Searches().findUserFamilyNameByUserNameDistinct("Paula")
                .collect(Collectors.toList()));
    }

    @Test
    void testFindUserFractionNumeratorByFamilyName() {
        assertEquals(List.of(2, 4, 0, 1, 1), new Searches().findFractionNumeratorByUserFamilyName("Torres")
                .collect(Collectors.toList()));
    }

    @Test
    void testFindFamilyNameByFractionDenominator() {
        assertEquals(List.of("López", "Torres"), new Searches().findUserFamilyNameByFractionDenominator(2)
                .collect(Collectors.toList()));
    }

    void testFindUserIdByAnyProperFraction() {
    }

    void testFindUserNameByAnyImproperFraction() {
    }

    void testFindUserFamilyNameByAllSignFractionDistinct() {
    }

    @Test
    void testFindDecimalFractionByUserName() {
        assertEquals(List.of(1.0, 1.0, Double.NaN, Double.POSITIVE_INFINITY, 1.0), new Searches().findDecimalFractionByUserName("Paula")
                .collect(Collectors.toList()));
    }

    void testFindDecimalFractionBySignFraction() {
    }

    @Test
    void testFindFractionAdditionByUserId() {
        assertEquals(new Fraction(8, 4), new Searches().findFractionAdditionByUserId("4"));
    }

    void testFindFractionSubtractionByUserName() {
        //pues ni uno ni otro chaval jeje
    }

    void testFindFractionMultiplicationByUserFamilyName() {
        //salu3 amigui2
    }
}
