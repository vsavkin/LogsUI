package com.victorsavkin.logviewer.domain.type

import spock.lang.Specification

class NumericTypeSpec extends Specification {

	def 'should parse a double based on a pattern'(){
		setup:
		def type = new NumericType()

		expect:
		type.parse('10.5') == 10.5
		type.parse('10') == 10d
	}

	def 'should tell if type is applicable to a string'(){
		setup:
		def type = new NumericType()

		expect:
		type.isApplicable(STR) == RESULT

		where:
		STR     | RESULT
		'10.5'  | true
		'10'    | true
		'string'| false
	}
}
