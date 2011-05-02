package com.victorsavkin.logviewer.domain

import com.victorsavkin.logviewer.domain.variable.VariableExtractionRule
import com.victorsavkin.logviewer.domain.variable.VariableExtractor

class TextParser {

    VariableExtractor extractor = new VariableExtractor()

    LineCollection parse(String filename, String text, List<VariableExtractionRule> rules){
        def lines = []
        text.eachLine {
            lines << createLine(it, rules)
        }
        new LineCollection(filename, lines)
    }

    private createLine(line, rules) {
        def vars = rules.collect {
            extractor.extract line, it
        }.findAll{it != null}
        new Line(line, vars)
    }
}
