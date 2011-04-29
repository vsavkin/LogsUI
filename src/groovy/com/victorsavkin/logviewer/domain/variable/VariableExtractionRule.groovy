package com.victorsavkin.logviewer.domain.variable

import com.victorsavkin.logviewer.domain.extractor.TextExtractor
import com.victorsavkin.logviewer.domain.type.Type

class VariableExtractionRule {
    String varName
    Type type
    TextExtractor textExtractor
}
