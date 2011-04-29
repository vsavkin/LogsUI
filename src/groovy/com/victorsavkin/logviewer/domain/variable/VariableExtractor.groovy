package com.victorsavkin.logviewer.domain.variable

class VariableExtractor {

    Variable extract(String line, VariableExtractionRule rule){
        def res = rule.textExtractor.extract(line)
        rule.type.isApplicable(res.text) ? createVar(rule.varName, rule.type, res) : null
    }

    private createVar(varName, type, res) {
        new Variable(varName, res.text, type, res.position)
    }
}
