package com.victorsavkin.logviewer.domain.extractor

import com.victorsavkin.logviewer.domain.PositionInLine

class ContainsTextExtractor extends TextExtractor {

	String substring

	ExtractResult extract(String line){
        def index = line.indexOf(substring)
        if(index > -1){
            createExtractResult index
        } else {
            EmptyExtractResult.instance
        }
	}

    private createExtractResult(index) {
        def position = new PositionInLine(index, index + substring.size())
        new ExtractResult(substring, position)
    }
}
