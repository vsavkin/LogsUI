package com.victorsavkin.logviewer.domain.field

import com.victorsavkin.logviewer.domain.PositionInLine
import com.victorsavkin.logviewer.domain.type.Type

class Field {
	final Type type
    final String name
	final String value
    final PositionInLine position

    Field(String name, String value, Type type, PositionInLine position) {
        this.type = type
        this.name = name
        this.value = value
        this.position = position
    }

    Object getParsedValue(){
        type.parse value
    }

    boolean equals(o) {
        if (!(o instanceof Field)) return false
        name == o.name && type == o.type && value == o.value && position == o.position
    }

    int hashCode() {
        int result = (type != null ? type.hashCode() : 0)
        result = 31 * result + (name != null ? name.hashCode() : 0)
        result = 31 * result + (value != null ? value.hashCode() : 0)
        31 * result + (position != null ? position.hashCode() : 0)
    }
}
