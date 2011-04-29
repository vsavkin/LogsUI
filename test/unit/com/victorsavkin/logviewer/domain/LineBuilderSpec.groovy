package com.victorsavkin.logviewer.domain

import com.victorsavkin.logviewer.domain.type.StringType
import spock.lang.Specification

class LineBuilderSpec extends Specification {

    def 'should build a line'(){
        when:
        def line = LineBuilder.line {
            text 'aaabbb'
            var name: 'a', type: 'string', value: 'aaa', from: 0, to: 3
            var name: 'b', type: 'string', value: 'bbb', from: 3, to: 6
        }

        then:
        line.text == 'aaabbb'
        line.variables.size() == 2
        def v = line.variables.first()
        v.name == 'a'
        v.value == 'aaa'
        v.type instanceof StringType
        v.position == new PositionInLine(0,3)
    }
}
