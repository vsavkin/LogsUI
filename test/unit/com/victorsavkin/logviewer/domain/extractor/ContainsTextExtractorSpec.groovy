package com.victorsavkin.logviewer.domain.extractor

import com.victorsavkin.logviewer.domain.PositionInLine
import spock.lang.Specification

class ContainsTextExtractorSpec extends Specification {

	def 'should return substring if it is inside a text line'(){
		setup:
		def extractor = new ContainsTextExtractor(substring: 'secret')

		expect:
		extractor.extract(LINE) == RESULT

		where:
		LINE                | RESULT
		'secret'            | new ExtractResult('secret', new PositionInLine(0, 6))
		'dont have secrets' | new ExtractResult('secret', new PositionInLine(10, 16))
		'another line'      | EmptyExtractResult.instance
		''                  | EmptyExtractResult.instance
	}
}
