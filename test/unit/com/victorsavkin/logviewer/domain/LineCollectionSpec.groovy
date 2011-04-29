package com.victorsavkin.logviewer.domain

import spock.lang.Specification
import static com.victorsavkin.logviewer.domain.LineBuilder.line

class LineCollectionSpec extends Specification {

    def 'should returned a collected filtered by a variable'(){
        setup:
        def c = new LineCollection([
            line {
                text '111'
                var name: 'a', type: 'string', value: '1', from: 0, to: 3
            },

            line {
                text '222'
                var name: 'b', type: 'string', value: '2', from: 0, to: 3
            },

            line {
                text '333'
                var name: 'a', type: 'string', value: '1', from: 0, to: 3
                var name: 'b', type: 'string', value: '2', from: 0, to: 3
            }])

        when:
        def filtered = c.findAllWhereVarExist('a')

        then:
        filtered.lines.size() == 2
        filtered.lines[0].text == '111'
        filtered.lines[1].text == '333'

        when:
        filtered = c.findAllWhereVarExist('d')

        then:
        filtered.lines.empty == true
    }

    def "should returned a collected filtered by variable's value"(){
        setup:
        def c = new LineCollection([
            line {
                text '111'
                var name: 'a', type: 'string', value: '1', from: 0, to: 3
            },

            line {
                text '222'
                var name: 'b', type: 'string', value: '2', from: 0, to: 3
            },

            line {
                text '333'
                var name: 'a', type: 'string', value: '3', from: 0, to: 3
                var name: 'b', type: 'string', value: '2', from: 0, to: 3
            }])

        when:
        def filtered = c.findAllWhereVarEq('a', '3')

        then:
        filtered.lines.size() == 1
        filtered.lines[0].text == '333'
    }
}
