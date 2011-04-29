package com.victorsavkin.logviewer.view

import spock.lang.Specification
import static com.victorsavkin.logviewer.domain.LineBuilder.line

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
                var name: 'a', type: 'string', value: 'aaa', from: 0, to: 3
            },

            line {
                text 'aaabbbccc'
                var name: 'c', type: 'string', value: 'aaa', from: 6, to: 9
            },

            line {
                text 'aaabbbccc'
                var name: 'a', type: 'string', value: 'aaa', from: 0, to: 3
                var name: 'c', type: 'string', value: 'aaa', from: 6, to: 9
            },

            line {
                text 'aaabbbccc'
            }
        ]

        GENHTML << [
            '<span class="variable">aaa</span>bbbccc',
            'aaabbb<span class="variable">ccc</span>',
            '<span class="variable">aaa</span>bbb<span class="variable">ccc</span>',
            'aaabbbccc'
        ]
    }
}
