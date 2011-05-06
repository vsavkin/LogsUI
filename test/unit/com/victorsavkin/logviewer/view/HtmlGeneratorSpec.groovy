package com.victorsavkin.logviewer.view

import spock.lang.Specification
import static com.victorsavkin.logviewer.domain.line.LineBuilder.line

class HtmlGeneratorSpec extends Specification {

    HtmlGenerator gen

    def setup(){
        gen = new HtmlGenerator()
    }

    def 'should generate html for a line'(){
        expect:
        gen.generateHtml(LINE) == GENHTML

        where:
        LINE << [
            line {
                text 'aaabbbccc'
                field name: 'a', type: 'string', value: 'aaa', from: 0, to: 3
            },

            line {
                text 'aaabbbccc'
                field name: 'c', type: 'string', value: 'aaa', from: 6, to: 9
            },

            line {
                text 'aaabbbccc'
                field name: 'a', type: 'string', value: 'aaa', from: 0, to: 3
                field name: 'c', type: 'string', value: 'aaa', from: 6, to: 9
            },

            line {
                text 'aaabbbccc'
            }
        ]

        GENHTML << [
            '<span class="field">aaa</span>bbbccc',
            'aaabbb<span class="field">ccc</span>',
            '<span class="field">aaa</span>bbb<span class="field">ccc</span>',
            'aaabbbccc'
        ]
    }
}
