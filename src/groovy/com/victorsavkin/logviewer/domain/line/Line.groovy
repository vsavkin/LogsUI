package com.victorsavkin.logviewer.domain.line

import com.victorsavkin.logviewer.domain.field.Field

class Line {
	final String text
    final List<Field> fields

    Line(String text, List<Field> fields) {
        this.text = text
        this.fields = fields
    }

    Field field(String name){
        fields.find {it.name == name}
    }

    boolean equals(o) {
        if (!(o instanceof Line)) return false
        text == o.text && fields == this.fields
    }

    int hashCode() {
        int result = (text != null ? text.hashCode() : 0)
        31 * result + (fields != null ? fields.hashCode() : 0)
    }
}
