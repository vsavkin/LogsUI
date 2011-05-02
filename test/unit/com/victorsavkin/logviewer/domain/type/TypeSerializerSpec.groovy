package com.victorsavkin.logviewer.domain.type

import spock.lang.Specification

class TypeSerializerSpec extends Specification {

    TypeSerializer serializer = new TypeSerializer()

    def 'should serialize types'(){
        expect:
        serializer.toString(TYPE) == TYPESTR
        serializer.parse(TYPESTR) == TYPE

        where:
        TYPESTR             |TYPE
        'void'              |new VoidType()
        'numeric'           |new NumericType()
        'string'            |new StringType()
        'date_2010-01-01'   |new DateType('2010-01-01')
    }
}
