package logviewer

import com.victorsavkin.logviewer.domain.line.LineCollection
import static com.victorsavkin.logviewer.domain.line.LineBuilder.*
import com.victorsavkin.logviewer.view.HtmlGenerator

class LogFileController {

    HtmlGenerator htmlGenerator

    def index = {
        def lineCollection = new LineCollection([
            line {
                text 'user1 | session1 | 2010-01-01 | some other information'
                field name: 'session', type: 'string', value: 'session1', from: 8, to: 17
            },

            line {
                text 'user2 | session2 | 2010-01-01 | some other information'
                field name: 'session', type: 'string', value: 'session2', from: 8, to: 17
            },

            line {
                text 'user3 | session3 | 2010-01-01 | some other information'
                field name: 'session', type: 'string', value: 'session3', from: 8, to: 17
            }
        ])

        render view: 'index', model: [lines: genHtmlForLines(lineCollection.lines), title: 'My Lines']
    }

    private genHtmlForLines(lines){
        lines.collect {
            htmlGenerator.generateHtml it
        }
    }
}
