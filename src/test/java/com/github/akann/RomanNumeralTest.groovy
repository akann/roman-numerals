package com.github.akann

/**
 * Created by akan on 23/09/2016.
 * Tests borrowed from Oladapo Fadeyi
 */
class RomanNumeralTest extends spock.lang.Specification {

    def "should return roman representation"(number, roman) {
        expect:
        roman == RomanNumeral.convert(number)

        where:
        number | roman
        1      | 'I'
        2      | 'II'
        3      | 'III'
        4      | 'IV'
        8      | 'VIII'
        36     | 'XXXVI'
        47     | 'XLVII'
        89     | 'LXXXIX'
        99     | 'XCIX'
        890    | 'DCCCXC'
        1800   | 'MDCCC'
        1990   | 'MCMXC'
        2008   | 'MMVIII'
    }

    def "should return decimal"(roman, number) {
        expect:
        number == RomanNumeral.convert(roman)

        where:
        roman    | number
        'I'      | 1
        'II'     | 2
        'III'    | 3
        'IV'     | 4
        'VIII'   | 8
        'XXXVI'  | 36
        'XLVII'  | 47
        'LXXXIX' | 89
        'XCIX'   | 99
        'DCCCXC' | 890
        'MDCCC'  | 1800
        'MCMXC'  | 1990
        'MMVIII' | 2008
    }

    def "should return an exception when converting 0"(){
        when:
        RomanNumeral.convert(0)

        then:
        RuntimeException ex = thrown()
        ex.message == 'Could not find number: 0'
    }

    def "should return an exception when converting a negative number"(){
        when:
        RomanNumeral.convert(-42)

        then:
        thrown(RuntimeException)
    }

}
