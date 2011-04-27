package com.victorsavkin.logviewer.view

import com.victorsavkin.logviewer.domain.variable.Variable
import com.victorsavkin.logviewer.domain.Line
import com.victorsavkin.logviewer.domain.PositionAdjustmentManager

class HtmlGenerator {

    String generateHtml(Line line){
        def res = line.text
        def m = new PositionAdjustmentManager(res.size())
        line.variables.each {
            res = highlightVariable(m, it, res)
            adjustPositions m, it
        }
        res
    }

    private adjustPositions(PositionAdjustmentManager m, Variable var) {
        m.insert var.position.start, spanOpenTag.size()
        m.insert var.position.end, spanCloseTag.size()
    }

    private highlightVariable(PositionAdjustmentManager m, Variable var, String line) {
        def s = m.getAdjustedPosition(var.position.start)
        def e = m.getAdjustedPosition(var.position.end - 1) + 1
        insertSpan line, s, e
    }

    private insertSpan(line, start, end){
        def res = line[0..<start] + spanOpenTag + line[start..<end] + spanCloseTag
        if(end < line.size())
            res += line[end..-1]
        res
    }

    private getSpanOpenTag() {
        '<span class="variable">'
    }

    private getSpanCloseTag() {
        '</span>'
    }
}
