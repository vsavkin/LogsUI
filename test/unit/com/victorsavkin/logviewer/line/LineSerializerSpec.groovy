package com.victorsavkin.logviewer.line

import spock.lang.Specification
import com.victorsavkin.logviewer.domain.field.Field
import com.victorsavkin.logviewer.domain.type.StringType
import com.victorsavkin.logviewer.domain.line.Line
import com.victorsavkin.logviewer.domain.line.LineSerializer
import com.victorsavkin.logviewer.domain.PositionInLine

class LineSerializerSpec extends Specification {

    def 'should serialize a line'(){
        setup:
        def serializer = new LineSerializer()

        expect:
        serializer.parse(serializer.toMap(COLNAME, LINE)) == LINE

        where:
        COLNAME = 'mycollection'
        LINE = new Line('some text', [new Field('a', 'b', new StringType(), new PositionInLine(0,5))])
    }
}
