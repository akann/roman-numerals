package com.github.akann

/**
 * Created by akan on 23/09/2016.
 * Tests borrowed from Oladapo Fadeyi
 */
class RomanNumeralsTest extends spock.lang.Specification {
    def RomanNumerals romanNumerals

    def setup() {
        romanNumerals = new RomanNumerals()
    }

    def "should return roman representation"(number, roman) {
        expect:
        roman == romanNumerals.convert(number)

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
        number == romanNumerals.convert(roman)

        where:
        roman    | number
        'i'      | 1000
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

    def "should return roman for highest decimals"(number, roman) {
        expect:
        roman  == romanNumerals.convert(number)
        number == romanNumerals.convert(roman)

        where:
        number | roman
        1      | 'I'
        4      | 'IV'
        5      | 'V'
        9      | 'IX'
        10     | 'X'
        40     | 'XL'
        50     | 'L'
        90     | 'XC'
        100    | 'C'
        400    | 'CD'
        500    | 'D'
        900    | 'CM'
        1000   | 'M'
    }


}
