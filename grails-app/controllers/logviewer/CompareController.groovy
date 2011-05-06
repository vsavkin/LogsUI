package logviewer

import com.victorsavkin.logviewer.view.HtmlGenerator
import com.victorsavkin.logviewer.domain.line.LineCollection
import com.victorsavkin.logviewer.domain.line.LineCollectionSyncStrategy

class CompareController {

    HtmlGenerator htmlGenerator
    LineCollectionSyncStrategy syncStrategy

    def index = {
        def lineCollection1 = new LineCollection([
                line {
                    text 'line11 | session1 | 2010-01-01 | some other information'
                    field name: 'session', type: 'string', value: 'session1', from: 8, to: 17
                },

                line {
                    text 'line12 | session2 | 2011-01-01 | some other information'
                    field name: 'session', type: 'string', value: 'session2', from: 8, to: 17
                }
        ])

        def lineCollection2 = new LineCollection([
                line {
                    text 'line21 | session1 | 2010-01-01 | some other information'
                    field name: 'session', type: 'string', value: 'session1', from: 8, to: 17
                },

                line {
                    text 'line22 | session2 | 2012-01-01 | some other information'
                    field name: 'session', type: 'string', value: 'session2', from: 8, to: 17
                }
        ])

        def collections = syncStrategy.sync([lineCollection1, lineCollection2])
        def lineGroups = collections.collect{
            [lines: genHtmlForLines(it.lines), title: it.name]
        }

        render view: 'index', model: [collections: lineGroups]
    }

    private genHtmlForLines(lines) {
        lines.collect {
            htmlGenerator.generateHtml it
        }
    }
}
