package com.victorsavkin.logviewer.domain.field

import com.victorsavkin.logviewer.domain.extractor.TextExtractor
import com.victorsavkin.logviewer.domain.type.Type

class FieldExtractionRule {
    String fieldName
    Type type
    TextExtractor textExtractor
}
