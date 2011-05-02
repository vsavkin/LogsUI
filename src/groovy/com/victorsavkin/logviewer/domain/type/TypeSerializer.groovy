package com.victorsavkin.logviewer.domain.type

class TypeSerializer {

    String toString(Type type){
        if(type instanceof VoidType)
            'void'
        else if (type instanceof NumericType)
            'numeric'
        else if (type instanceof StringType)
            'string'
        else if (type instanceof DateType)
            "date_${type.format}"
        else
            throw new RuntimeException("invalid type: ${type}")
    }

    Type parse(String str){
        switch(str){
            case 'void': return new VoidType()
            case 'numeric': return new NumericType()
            case 'string': return new StringType()
            default:
                if(str.startsWith('date_'))
                    return new DateType(str[5..-1])
                else
                    throw new RuntimeException("invalid type ${str}")
        }
    }
}
