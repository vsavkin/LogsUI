package com.victorsavkin.logviewer.line

import spock.lang.Specification
import static com.victorsavkin.logviewer.domain.line.LineBuilder.line
import com.victorsavkin.logviewer.domain.line.LineCollection

class LineCollectionSpec extends Specification {

    def 'should returned a collected filtered by a variable'(){
        setup:
        def c = new LineCollection('name', [
            line {
                text '111'
                field name: 'a', type: 'string', value: '1', from: 0, to: 3
            },

            line {
                text '222'
                field name: 'b', type: 'string', value: '2', from: 0, to: 3
            },

            line {
                text '333'
                field name: 'a', type: 'string', value: '1', from: 0, to: 3
                field name: 'b', type: 'string', value: '2', from: 0, to: 3
            }])

        when:
        def filtered = c.findAllWhereFieldExist('a')

        then:
        filtered.lines.size() == 2
        filtered.lines[0].text == '111'
        filtered.lines[1].text == '333'

        when:
        filtered = c.findAllWhereFieldExist('d')

        then:
        filtered.lines.empty == true
    }

    def "should returned a collected filtered by variable's value"(){
        setup:
        def c = new LineCollection('name', [
            line {
                text '111'
                field name: 'a', type: 'string', value: '1', from: 0, to: 3
            },

            line {
                text '222'
                field name: 'b', type: 'string', value: '2', from: 0, to: 3
            },

            line {
                text '333'
                field name: 'a', type: 'string', value: '3', from: 0, to: 3
                field name: 'b', type: 'string', value: '2', from: 0, to: 3
            }])

        when:
        def filtered = c.findAllWhereFieldEq('a', '3')

        then:
        filtered.lines.size() == 1
        filtered.lines[0].text == '333'
    }
}
