package com.victorsavkin.logviewer.domain.extractor

import com.victorsavkin.logviewer.domain.PositionInLine
import java.util.regex.Pattern

class RegexTextExtractor extends TextExtractor{

    String pattern

    ExtractResult extract(String line) {
        def p = Pattern.compile(pattern)
        def m = p.matcher(line)
        m ? createExtractResult(m) : EmptyExtractResult.instance
    }

    private createExtractResult(m) {
        def position = new PositionInLine(m.start(0), m.end(0))
        new ExtractResult(m.group(0), position)
    }
}
