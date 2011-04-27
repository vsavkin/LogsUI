package com.victorsavkin.logviewer.domain.type

import spock.lang.Specification

class VoidTypeSpec extends Specification {

    VoidType type

    def setup(){
        type = new VoidType()
    }

    def 'should return an empty string'(){
        expect:
        type.parse('some string') == ''
    }

    def 'should throw an exception if null is passed'(){
        when:
        type.parse(null)

        then:
        thrown(NullPointerException)
    }
}
