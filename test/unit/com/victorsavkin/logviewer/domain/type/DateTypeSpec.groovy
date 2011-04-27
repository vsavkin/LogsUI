package com.victorsavkin.logviewer.domain.type

import spock.lang.Specification
import java.text.DateFormat
import java.text.SimpleDateFormat

class DateTypeSpec extends Specification {

	def 'should parse a date based on a pattern'(){
		setup:
		def type = new DateType(format: 'yyyy-mm-dd')
		def format = new SimpleDateFormat('yyyy-mm-dd')

		expect:
		type.parse('2010-11-12') == format.parse('2010-11-12')
	}

	def 'should tell if type is applicable to a string'(){
		setup:
		def type = new DateType(format: 'yyyy-mm-dd')

		expect:
		type.isApplicable(STR) == RESULT

		where:
		STR | RESULT
		'2010-11-12' | true
		'2010-01-01' | true
		'2010/11/12' | false
		'just a string' | false
	}
}
