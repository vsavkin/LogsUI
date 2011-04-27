package com.victorsavkin.logviewer.domain.extractor

class ContainsTextExtractor extends TextExtractor{
	String substring

	String extract(String line){
		line.contains(substring) ? substring : null
	}
}
