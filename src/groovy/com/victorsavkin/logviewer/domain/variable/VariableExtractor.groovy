package com.victorsavkin.logviewer.domain.variable

import com.victorsavkin.logviewer.domain.extractor.TextExtractor
import com.victorsavkin.logviewer.domain.type.Type
import com.victorsavkin.logviewer.domain.extractor.ExtractResult

class VariableExtractor {

    Variable extract(String line, VariableExtractionRule rule){
        def res = rule.textExtractor.extract(line)
        rule.type.isApplicable(res.text) ? createVar(rule.varName, rule.type, res) : null
    }

    private createVar(varName, type, res) {
        new Variable(varName, res.text, type, res.position)
    }
}
