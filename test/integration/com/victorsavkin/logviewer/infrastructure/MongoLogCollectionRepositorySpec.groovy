package com.victorsavkin.logviewer.infrastructure

import grails.plugin.spock.IntegrationSpec
import com.victorsavkin.logviewer.domain.LineCollection
import static com.victorsavkin.logviewer.domain.LineBuilder.line
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import com.mongodb.DBCollection
import com.victorsavkin.logviewer.domain.type.DateType

class MongoLogCollectionRepositorySpec extends IntegrationSpec {

    def mongo //injected by Grails

    MongoLogCollectionRepository repo

    def setup(){
        repo = new MongoLogCollectionRepository(mongo.getDB('testing'))
    }

    def cleanup(){
        repo.clear()
    }
    
    def 'should save line collections'(){
        setup:
        def c = new LineCollection('name', [])

        when:
        repo.insert(c)

        then:
        repo.numberOfCollections == 1
    }
    
    def 'should find line collections by name'(){
        setup:
        repo.insert new LineCollection('aaa1', [])
        repo.insert new LineCollection('bbb1', [])
        repo.insert new LineCollection('aaa2', [])

        when:
        def r = repo.findAll(new Criteria(filename: ~/^aaa/))

        then:
        r.size() == 2
        r.name == ['aaa1', 'aaa2']
    }

    def 'should return all rows of a found collection'(){
        setup:
        repo.insert new LineCollection('aaa', [
            line {
                text 'line1'
                var name: 'var1', type: 'yyyy-mm-dd', value: '2010-01-01', from: 0, to: 1
            },
            line {text 'line2'}
        ])

        repo.insert new LineCollection('bbb', [])

        when:
        def r = repo.findAll(new Criteria(filename: ~/^aaa/))

        then:
        r.size() == 1
        r.first().name == 'aaa'
        r.first().lines.text == ['line1', 'line2']
        def firstLine = r.first().lines.first()
        firstLine.variables.first().name == 'var1'
        (firstLine.variables.first().type instanceof DateType) == true
    }
}
