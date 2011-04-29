package com.victorsavkin.logviewer.domain.variable

import com.victorsavkin.logviewer.domain.PositionInLine
import com.victorsavkin.logviewer.domain.extractor.ContainsTextExtractor
import com.victorsavkin.logviewer.domain.type.NumericType
import com.victorsavkin.logviewer.domain.type.StringType
import spock.lang.Specification

class VariableExtractorSpec extends Specification {

	def 'should extract a variable'(){
		setup:
		def ex = new VariableExtractor()
		def line = 'aaaa value bbbb'

        def rule = new VariableExtractionRule(
            varName: 'varname',
            type: new StringType(),
            textExtractor: new ContainsTextExtractor(substring: 'value'))
        
		when:
		def var = ex.extract(line, rule)

		then:
        var.name == 'varname'
		var.type instanceof StringType
		var.value == 'value'
        var.position == new PositionInLine(5, 10)
	}

	def 'should return null if it cant extract text from a line'(){
		setup:
		def ex = new VariableExtractor()
		def line = 'aaaa bbbb'

        def rule = new VariableExtractionRule(
            varName: 'varname',
            type: new StringType(),
            textExtractor: new ContainsTextExtractor(substring: 'value'))
        
		expect:
		ex.extract(line, rule) == null
	}

	def 'should return null if passed type cant be applied to extracted text'(){
		setup:
		def ex = new VariableExtractor()
		def line = 'aaaa value bbbb'

        def rule = new VariableExtractionRule(
            varName: 'varname',
            type: new NumericType(),
            textExtractor: new ContainsTextExtractor(substring: 'value'))

		expect:
		ex.extract(line, rule) == null
	}
}
