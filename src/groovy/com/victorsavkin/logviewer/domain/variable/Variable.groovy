package com.victorsavkin.logviewer.domain.variable

import com.victorsavkin.logviewer.domain.PositionInLine
import com.victorsavkin.logviewer.domain.type.Type

class Variable {
	final Type type
    final String name
	final String value
    final PositionInLine position

    Variable(String name, String value, Type type, PositionInLine position) {
        this.type = type
        this.name = name
        this.value = value
        this.position = position
    }
}
