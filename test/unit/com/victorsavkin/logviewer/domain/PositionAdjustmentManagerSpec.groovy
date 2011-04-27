package com.victorsavkin.logviewer.domain

import spock.lang.Specification

class PositionAdjustmentManagerSpec extends Specification {

    def 'should return adjusted position'(){
        setup:
        def m = new PositionAdjustmentManager(10)

        when:
        m.insert(5,10)

        then:
        m.getAdjustedPosition(4) == 4
        m.getAdjustedPosition(5) == 15
        m.getAdjustedPosition(9) == 19

        when:
        m.insert(3, 10)

        then:
        m.getAdjustedPosition(2) == 2
        m.getAdjustedPosition(3) == 13
        m.getAdjustedPosition(5) == 25
    }

    def 'should handle boundary values'(){
        setup:
        def m = new PositionAdjustmentManager(10)

        when:
        m.insert(10,10)

        then:
        m.getAdjustedPosition(9) == 9

        when:
        m.insert(0,10)

        then:
        m.getAdjustedPosition(0) == 10
    }
}
