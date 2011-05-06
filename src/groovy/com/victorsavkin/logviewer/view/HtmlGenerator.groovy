package com.victorsavkin.logviewer.view

import com.victorsavkin.logviewer.domain.line.Line
import com.victorsavkin.logviewer.domain.PositionAdjustmentManager
import com.victorsavkin.logviewer.domain.field.Field

class HtmlGenerator {

    String generateHtml(Line line){
        def res = line.text
        def m = new PositionAdjustmentManager(res.size())
        line.fields.each {
            res = highlightField(m, it, res)
            adjustPositions m, it
        }
        res
    }

    private adjustPositions(PositionAdjustmentManager m, Field field) {
        m.insert field.position.start, spanOpenTag.size()
        m.insert field.position.end, spanCloseTag.size()
    }

    private highlightField(PositionAdjustmentManager m, Field field, String line) {
        def s = m.getAdjustedPosition(field.position.start)
        def e = m.getAdjustedPosition(field.position.end - 1) + 1
        insertSpan line, s, e
    }

    private insertSpan(line, start, end){
        def res = line[0..<start] + spanOpenTag + line[start..<end] + spanCloseTag
        if(end < line.size())
            res += line[end..-1]
        res
    }

    private getSpanOpenTag() {
        '<span class="field">'
    }

    private getSpanCloseTag() {
        '</span>'
    }
}
