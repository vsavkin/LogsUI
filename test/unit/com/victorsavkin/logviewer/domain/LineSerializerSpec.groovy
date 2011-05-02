package com.victorsavkin.logviewer.domain

import spock.lang.Specification
import com.victorsavkin.logviewer.domain.variable.Variable
import com.victorsavkin.logviewer.domain.type.StringType

class LineSerializerSpec extends Specification {

    def 'should serialize a line'(){
        setup:
        def serializer = new LineSerializer()

        expect:
        serializer.parse(serializer.toMap(COLNAME, LINE)) == LINE

        where:
        COLNAME = 'mycollection'
        LINE = new Line('some text', [new Variable('a', 'b', new StringType(), new PositionInLine(0,5))])
    }
}
