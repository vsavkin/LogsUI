package com.victorsavkin.logviewer.domain.field

class FieldExtractor {

    Field extract(String line, FieldExtractionRule rule){
        def res = rule.textExtractor.extract(line)
        rule.type.isApplicable(res.text) ? createField(rule.fieldName, rule.type, res) : null
    }

    private createField(varName, type, res) {
        new Field(varName, res.text, type, res.position)
    }
}
