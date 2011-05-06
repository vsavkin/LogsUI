package com.victorsavkin.logviewer.domain.line

import com.victorsavkin.logviewer.domain.type.TypeSerializer
import com.victorsavkin.logviewer.domain.field.Field
import com.victorsavkin.logviewer.domain.PositionInLine

class LineSerializer {

    final typeSerializer = new TypeSerializer()

    Map toMap(String collectionName, Line line) {
        def fields = line.fields.collect {createMapForField(it, typeSerializer)}
        [collectionName: collectionName, text: line.text, fields: fields]
    }

    Line parse(Map map) {
        def fields = map.fields.collect {
            def pos = it.position
            new Field(it.name, it.value, typeSerializer.parse(it.type), new PositionInLine(pos.from, pos.to))
        }
        new Line(map.text, fields)
    }

    private createMapForField(field, typeSerializer) {
        def pos = [from: field.position.start, to: field.position.end]
        [name: field.name, position: pos, type: typeSerializer.toString(field.type), value: field.value]
    }
}
