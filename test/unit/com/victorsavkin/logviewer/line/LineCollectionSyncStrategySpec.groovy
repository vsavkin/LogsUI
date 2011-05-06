package com.victorsavkin.logviewer.line

import spock.lang.Specification
import com.victorsavkin.logviewer.domain.line.DateBasedLineCollectionSyncStrategy
import static com.victorsavkin.logviewer.domain.line.LineBuilder.*
import com.victorsavkin.logviewer.domain.line.LineCollection
import com.victorsavkin.logviewer.domain.line.EmptyLine
import com.victorsavkin.logviewer.domain.line.LineCollectionSyncStrategy

class LineCollectionSyncStrategySpec extends Specification {

    LineCollectionSyncStrategy strategy

    def setup(){
        strategy = new DateBasedLineCollectionSyncStrategy()
    }

    def 'should synchronize two collections'(){
        when:
        def r = strategy.sync([C1, C2])

        then:
        r.size() == 2
        r.name == [C1.name, C2.name]

        def lines1 = r[0].lines
        def lines2 = r[1].lines

        then:
        lines1.size() == 3
        lines2.size() == 3

        lines1[0].text == 'line11'
        lines1[0].field('date1').value == '2010-01-01'
        lines1[1] instanceof EmptyLine
        lines1[1].field('date1').value == '2011-01-01'
        lines1[2].text == 'line12'
        lines1[2].field('date1').value == '2012-01-01'

        lines2[0].text == 'line21'
        lines2[0].field('date2').value == '2010-01-01'
        lines2[1].text == 'line22'
        lines2[1].field('date2').value == '2011-01-01'
        lines2[2] instanceof EmptyLine
        lines2[2].field('date2').value == '2012-01-01'

        where:
        LINES1 = [
            line {
                text 'line11'
                field name: 'date1', type: 'yyyy-mm-dd', value: '2010-01-01', from: 0, to: 5
            },

            line {
                text 'line12'
                field name: 'date1', type: 'yyyy-mm-dd', value: '2012-01-01', from: 0, to: 5
            }
        ]

        LINES2 = [
            line {
                text 'line21'
                field name: 'date2', type: 'yyyy-mm-dd', value: '2010-01-01', from: 0, to: 5
            },

            line {
                text 'line22'
                field name: 'date2', type: 'yyyy-mm-dd', value: '2011-01-01', from: 0, to: 5
            }
        ]

        C1 = new LineCollection("line1", LINES1)
        C2 = new LineCollection("line2", LINES2)
    }

    def 'should not change an empty collection'(){
        when:
        def r = strategy.sync([C1, C2])
        def lines1 = r[0].lines
        def lines2 = r[1].lines

        then:
        lines1.size() == 1
        lines2.size() == 0

        where:
        LINES1 = [
            line {
                text 'text'
                field name: 'date1', type: 'yyyy-mm-dd', value: '2010-01-01', from: 0, to: 5
            }
        ]

        LINES2 = []

        C1 = new LineCollection("line1", LINES1)
        C2 = new LineCollection("line2", LINES2)
    }

    def 'should not change collections with date fields'(){
        when:
        def r = strategy.sync([C1, C2])
        def lines1 = r[0].lines
        def lines2 = r[1].lines

        then:
        lines1.size() == 1
        lines2.size() == 1

        where:
        LINES1 = [
            line {
                text 'text'
                field name: 'date1', type: 'yyyy-mm-dd', value: '2010-01-01', from: 0, to: 5
            }
        ]

        LINES2 = [
            line {
                text 'text'
            }
        ]


        C1 = new LineCollection("line1", LINES1)
        C2 = new LineCollection("line2", LINES2)
    }

}
