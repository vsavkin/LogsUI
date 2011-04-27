package com.victorsavkin.logviewer.domain.extractor

import spock.lang.Specification

class ContainsTextExtractorSpec extends Specification {

	def 'should return substring if it is inside a text line'(){
		setup:
		def extractor = new ContainsTextExtractor(substring: 'secret')

		expect:
		extractor.extract(LINE) == RESULT

		where:
		LINE                | RESULT
		'dont have secrets' | 'secret'
		'another line'      | null
		''                  | null
	}
}
