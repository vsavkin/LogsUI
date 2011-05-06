package com.victorsavkin.logviewer.domain

import com.victorsavkin.logviewer.domain.extractor.RegexTextExtractor
import com.victorsavkin.logviewer.domain.type.DateType
import com.victorsavkin.logviewer.domain.field.FieldExtractionRule
import spock.lang.Specification

class TextParserSpec extends Specification {

    def 'should parse text and return a line collection'(){
        setup:
        def p = new TextParser()
        def e = new RegexTextExtractor(pattern: /\d\d\d\d-\d\d-\d\d/)
        def r = new FieldExtractionRule(fieldName: 'date', type: new DateType('yyyy-MM-dd'), textExtractor: e)

        when:
        def lines = p.parse(FILENAME, TEXT, [r]).lines

        then:
        lines.size() == 3
        lines[0].fields.size() == 1
        def v0 = lines[0].fields[0]
        v0.type instanceof DateType
        v0.value == '2010-01-01'

        lines[1].fields[0].value == '2010-02-02'

        lines[2].fields.empty == true

        where:
        FILENAME = 'myfile'
        TEXT = '''
        server1|2010-01-01|description1
        server2|2010-02-02|description2
        server2|not a date|description2
        '''.stripIndent().trim()
    }
}
