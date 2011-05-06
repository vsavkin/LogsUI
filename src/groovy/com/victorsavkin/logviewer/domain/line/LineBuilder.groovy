package com.victorsavkin.logviewer.domain.line

import com.victorsavkin.logviewer.domain.type.DateType
import com.victorsavkin.logviewer.domain.type.NumericType
import com.victorsavkin.logviewer.domain.type.StringType
import com.victorsavkin.logviewer.domain.type.VoidType
import com.victorsavkin.logviewer.domain.field.Field
import com.victorsavkin.logviewer.domain.PositionInLine

class LineBuilder {
    private String text
    private List<Field> fields = []

    static Line line(Closure c){
        def b = new LineBuilder()
        c.delegate = b
        c()
        new Line(b.text, b.fields)
    }

    void text(t){
        text = t
    }

    void field(m){
        def pos = new PositionInLine(m.from, m.to)
        fields << new Field(m.name, m.value, getType(m.type), pos)
    }

    private getType(type){
        switch(type){
            case 'string': return new StringType()
            case 'numeric': return new NumericType()
            case 'void': return new VoidType()
            default: return new DateType(type)
        }
        throw new RuntimeException("Invalid type ${type}")
    }
}
