package com.victorsavkin.logviewer.domain.parser

import spock.lang.Specification
import java.util.concurrent.locks.Condition
import com.victorsavkin.logviewer.domain.extractor.ContainsTextExtractor
import com.victorsavkin.logviewer.domain.type.StringType

class ParserSpec extends Specification {

	def 'should parse a variable'(){
		setup:
		def parser = new Parser()
		def line = 'aaaa value bbbb'

		when:
		def var = parser.parse(line, new ContainsTextExtractor(substring: 'value'), new StringType())

		then:
		var.type instanceof StringType
		var.value == 'value'
	}
}
