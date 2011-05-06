package com.victorsavkin.logviewer.domain.line

import com.victorsavkin.logviewer.domain.type.DateType
import com.victorsavkin.logviewer.domain.field.Field

class DateTypeCollectionMixin {
    List<Date> getAllDateValues(String fieldName){
        lines*.field(fieldName).findAll {it}.parsedValue
    }

    Field findFirstDateField() {
        for (line in lines) {
            def dateField = findFirstDateField(line)
            if (dateField)
                return dateField
        }
    }

    private findFirstDateField(line) {
        line.fields.find {it.type instanceof DateType}
    }

    boolean hasDateField(){
        findFirstDateField() != null
    }
}
