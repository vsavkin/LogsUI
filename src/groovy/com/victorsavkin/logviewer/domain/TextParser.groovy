package com.victorsavkin.logviewer.domain

import com.victorsavkin.logviewer.domain.field.FieldExtractionRule
import com.victorsavkin.logviewer.domain.field.FieldExtractor
import com.victorsavkin.logviewer.domain.line.LineCollection
import com.victorsavkin.logviewer.domain.line.Line

class TextParser {

    FieldExtractor extractor = new FieldExtractor()

    LineCollection parse(String filename, String text, List<FieldExtractionRule> rules){
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
