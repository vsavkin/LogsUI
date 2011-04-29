package com.victorsavkin.logviewer.domain.extractor

import com.victorsavkin.logviewer.domain.PositionInLine

class ExtractResult {
    final String text
    final PositionInLine position

    ExtractResult(String text, PositionInLine position) {
        this.text = text
        this.position = position
    }

    boolean isEmpty() {
        false
    }

    String toString() {
        "ExtractResul ${text}, ${position}"
    }

    boolean equals(o) {
        if (!(o instanceof ExtractResult)) return false
        text == o.text && position == o.position
    }


    int hashCode() {
        int result = (text != null ? text.hashCode() : 0)
        31 * result + (position != null ? position.hashCode() : 0)
    }
}

@Singleton
class EmptyExtractResult extends ExtractResult {

    EmptyExtractResult() {
        super(null, null)
    }

    boolean isEmpty() {
        true
    }
}
