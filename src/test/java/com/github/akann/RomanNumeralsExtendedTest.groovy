package com.github.akann

/**
 * Created by akan on 23/09/2016.
 * Tests borrowed from Oladapo Fadeyi
 */
class RomanNumeralsExtendedTest extends spock.lang.Specification {
    def RomanNumeralsExtended romanNumeralsExtended

    def setup() {
        romanNumeralsExtended = new RomanNumeralsExtended()
    }

    def "should return roman representation"(number, roman) {
        expect:
        roman == romanNumeralsExtended.convert(number)
        number == romanNumeralsExtended.convert(roman)

        where:
        number     | roman
        39946      | 'xxxixCMXLVI'
        58594      | 'lvMMMDXCIV'
        445533     | 'cdxlvDXXXIII'
        998555     | 'cmxcvMMMDLV'
        99933      | 'xcixCMXXXIII'

    }

    def "should return roman for highest decimals"(number, roman) {
        expect:
        roman  == romanNumeralsExtended.convert(number)
        number == romanNumeralsExtended.convert(roman)

        where:
        number     | roman
        4_000      | 'iv'
        5_000      | 'v'
        9_000      | 'ix'
        10_000     | 'x'
        40_000     | 'xl'
        50_000     | 'l'
        90_000     | 'xc'
        100_000    | 'c'
        400_000    | 'cd'
        500_000    | 'd'
        900_000    | 'cm'
        1000_000   | 'm'
    }
}
