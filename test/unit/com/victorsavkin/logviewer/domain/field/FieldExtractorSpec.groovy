package com.victorsavkin.logviewer.domain.field

import com.victorsavkin.logviewer.domain.PositionInLine
import com.victorsavkin.logviewer.domain.extractor.ContainsTextExtractor
import com.victorsavkin.logviewer.domain.type.NumericType
import com.victorsavkin.logviewer.domain.type.StringType
import spock.lang.Specification

class FieldExtractorSpec extends Specification {

	def 'should extract a field'(){
		setup:
		def ex = new FieldExtractor()
		def line = 'aaaa value bbbb'

        def rule = new FieldExtractionRule(
            fieldName: 'fieldname',
            type: new StringType(),
            textExtractor: new ContainsTextExtractor(substring: 'value'))
        
		when:
		def field = ex.extract(line, rule)

		then:
        field.name == 'fieldname'
		field.type instanceof StringType
		field.value == 'value'
        field.position == new PositionInLine(5, 10)
	}

	def 'should return null if it cant extract text from a line'(){
		setup:
		def ex = new FieldExtractor()
		def line = 'aaaa bbbb'

        def rule = new FieldExtractionRule(
            fieldName: 'fieldname',
            type: new StringType(),
            textExtractor: new ContainsTextExtractor(substring: 'value'))
        
		expect:
		ex.extract(line, rule) == null
	}

	def 'should return null if passed type cant be applied to extracted text'(){
		setup:
		def ex = new FieldExtractor()
		def line = 'aaaa value bbbb'

        def rule = new FieldExtractionRule(
            fieldName: 'fieldname',
            type: new NumericType(),
            textExtractor: new ContainsTextExtractor(substring: 'value'))

		expect:
		ex.extract(line, rule) == null
	}
}
