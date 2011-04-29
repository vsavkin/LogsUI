package com.victorsavkin.logviewer.domain

class PositionInLine {
    final int start
    final int end

    PositionInLine(int start, int end) {
        this.start = start
        this.end = end
    }

    boolean equals(o) {
        if (!(o instanceof PositionInLine)) return false
        start == o.start && end == o.end
    }

    int hashCode() {
        int result = start
        31 * result + end
    }

    String toString() {
        "PositionInLine(${start}, ${end})"
    }
}
