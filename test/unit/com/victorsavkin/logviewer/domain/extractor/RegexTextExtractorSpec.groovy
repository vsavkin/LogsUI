package com.victorsavkin.logviewer.domain.extractor

import com.victorsavkin.logviewer.domain.PositionInLine
import spock.lang.Specification

class RegexTextExtractorSpec extends Specification {

    def 'should return a string matching regular expression'(){
        setup:
        def ex = new RegexTextExtractor(pattern: /\d+/)

        expect:
        ex.extract(STR) == RES

        where:
        STR         |RES
        '12345'     |new ExtractResult('12345', new PositionInLine(0, 5))
        'hel123lo'  |new ExtractResult('123', new PositionInLine(3, 6))
        'no digits' |EmptyExtractResult.instance
    }
}
