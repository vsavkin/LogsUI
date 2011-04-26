package com.victorsavkin.logviewer.domain.type

import spock.lang.Specification
import java.text.DateFormat
import java.text.SimpleDateFormat

class DateTypeSpec extends Specification {

	def 'should parse date based on a pattern'(){
		setup:
		def type = new DateType(format: 'yyyy-mm-dd')
		def format = new SimpleDateFormat('yyyy-mm-dd')

		expect:
		type.parse('2010-11-12') == format.parse('2010-11-12')
	}
}
