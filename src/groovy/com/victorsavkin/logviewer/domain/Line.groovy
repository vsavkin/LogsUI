package com.victorsavkin.logviewer.domain

import com.victorsavkin.logviewer.domain.variable.Variable

class Line {
	final String text
    final List<Variable> variables

    Line(String text, List<Variable> variables) {
        this.text = text
        this.variables = variables
    }
}
