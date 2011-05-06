package com.victorsavkin.logviewer.domain.line

import com.victorsavkin.logviewer.domain.field.Field
import com.victorsavkin.logviewer.domain.PositionInLine

class DateBasedLineCollectionSyncStrategy implements LineCollectionSyncStrategy {

    List<LineCollection> sync(List<LineCollection> list) {
        mixDateTypeOperationsToAllCollections list

        def dates = getAllDates(list)
        list.collect {
            it.hasDateField() ? getCollectionWithFilledGaps(it, dates) : it
        }
    }

    private getCollectionWithFilledGaps(LineCollection c, List<Date> dates) {
        def dateField = c.findFirstDateField()
        def iter = c.lines.iterator()
        def lines = getListOfLinesWithFilledGaps(iter, dateField, dates)
        new LineCollection(c.name, lines)
    }

    private getListOfLinesWithFilledGaps(Iterator lineIter, dateField, dates){
        def line = nextLine(lineIter)
        dates.collect {currDate ->
            if (!line || currDate < getParsedDate(line, dateField.name)) {
                createEmptyLine(dateField, currDate)
            } else {
                def res = line
                line = nextLine(lineIter)
                res
            }
        }
    }

    private getParsedDate(line, fieldName) {
        line.field(fieldName)?.parsedValue
    }

    private nextLine(iter) {
        iter.hasNext() ? iter.next() : null
    }

    private mixDateTypeOperationsToAllCollections(list) {
        list.each {
            it.metaClass.mixin DateTypeCollectionMixin
        }
    }

    private createEmptyLine(field, date) {
        def type = field.type
        def pos = new PositionInLine(0, 0)
        new EmptyLine(new Field(field.name, type.toString(date), type, pos))
    }

    private getAllDates(collections) {
        def dates = []
        collections.each {c ->
            if (c.hasDateField()) {
                def field = c.findFirstDateField()
                dates.addAll c.getAllDateValues(field.name)
            }
        }
        dates.unique().sort()
    }
}