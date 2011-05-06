package com.victorsavkin.logviewer.line

import com.victorsavkin.logviewer.domain.type.StringType
import spock.lang.Specification
import com.victorsavkin.logviewer.domain.line.LineBuilder
import com.victorsavkin.logviewer.domain.PositionInLine

class LineBuilderSpec extends Specification {

    def 'should build a line'(){
        when:
        def line = LineBuilder.line {
            text 'aaabbb'
            field name: 'a', type: 'string', value: 'aaa', from: 0, to: 3
            field name: 'b', type: 'string', value: 'bbb', from: 3, to: 6
        }

        then:
        line.text == 'aaabbb'
        line.fields.size() == 2
        def v = line.fields.first()
        v.name == 'a'
        v.value == 'aaa'
        v.type instanceof StringType
        v.position == new PositionInLine(0,3)
    }
}
