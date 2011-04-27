package com.victorsavkin.logviewer.domain

import com.victorsavkin.logviewer.domain.variable.Variable
import com.victorsavkin.logviewer.domain.type.StringType
import com.victorsavkin.logviewer.domain.type.NumericType
import com.victorsavkin.logviewer.domain.type.VoidType
import com.victorsavkin.logviewer.domain.type.DateType

class LineBuilder {
    private String text
    private List<Variable> vars = []

    static Line line(Closure c){
        def b = new LineBuilder()
        c.delegate = b
        c()
        new Line(b.text, b.vars)
    }

    void text(t){
        text = t
    }

    void var(m){
        def pos = new PositionInLine(m.from, m.to)
        vars << new Variable(m.name, m.value, getType(m.type), pos)
    }

    private getType(type){
        switch(type){
            case 'string': return new StringType()
            case 'numeric': return new NumericType()
            case 'void': return new VoidType()
            default: return new DateType(type)
        }
        assert false, "Invalid type ${type}"
    }
}
