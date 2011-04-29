package com.victorsavkin.logviewer.domain.type

import spock.lang.Specification
import com.victorsavkin.logviewer.domain.ParsingException

class StringTypeSpec extends Specification {

    StringType type

    def setup(){
        type = new StringType()
    }

    def 'should return passed string'(){
        expect:
        type.parse('str') == 'str'
    }

    def 'should throw an exception is null is passed'(){
        when:
        type.parse(null)

        then:
        thrown(ParsingException)
    }
}
