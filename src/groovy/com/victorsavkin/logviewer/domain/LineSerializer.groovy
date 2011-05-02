package com.victorsavkin.logviewer.domain

import com.victorsavkin.logviewer.domain.type.TypeSerializer
import com.victorsavkin.logviewer.domain.variable.Variable

class LineSerializer {

    final typeSerializer = new TypeSerializer()

    Map toMap(String collectionName, Line line) {
        def vars = line.variables.collect {createMapForVariable(it, typeSerializer)}
        [collectionName: collectionName, text: line.text, variables: vars]
    }

    Line parse(Map map) {
        def vars = map.variables.collect {
            def pos = it.position
            new Variable(it.name, it.value, typeSerializer.parse(it.type), new PositionInLine(pos.from, pos.to))
        }
        new Line(map.text, vars)
    }

    private createMapForVariable(var, typeSerializer) {
        def pos = [from: var.position.start, to: var.position.end]
        [name: var.name, position: pos, type: typeSerializer.toString(var.type), value: var.value]
    }
}
