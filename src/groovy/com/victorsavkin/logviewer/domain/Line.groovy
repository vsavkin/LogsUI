package com.victorsavkin.logviewer.domain

import com.victorsavkin.logviewer.domain.variable.Variable

class Line {
	final String text
    final List<Variable> variables

    Line(String text, List<Variable> variables) {
        this.text = text
        this.variables = variables
    }


    boolean equals(o) {
        if (!(o instanceof Line)) return false
        text == o.text && variables == o.variables
    }

    int hashCode() {
        int result = (text != null ? text.hashCode() : 0)
        31 * result + (variables != null ? variables.hashCode() : 0)
    }
}
