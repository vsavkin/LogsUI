package com.victorsavkin.logviewer.domain.variable

import com.victorsavkin.logviewer.domain.type.Type
import com.victorsavkin.logviewer.domain.extractor.TextExtractor

class VariableExtractionRule {
    String varName
    Type type
    TextExtractor textExtractor
}
