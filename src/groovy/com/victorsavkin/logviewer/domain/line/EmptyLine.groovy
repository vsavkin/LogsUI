package com.victorsavkin.logviewer.domain.line

import com.victorsavkin.logviewer.domain.field.Field

class EmptyLine extends Line {

    EmptyLine(Field dateField) {
        super('', [dateField])
    }
}
