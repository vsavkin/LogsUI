package com.victorsavkin.logviewer.domain

class PositionAdjustmentManager {

    private initial = []

    PositionAdjustmentManager(int initialSize){
        initialSize.times {
            initial << it
        }
    }

    void insert(int position, int length){
        def i = initial.indexOf(position)
        if(i == -1) return
        length.times {
            initial.add i, null
        }
    }

    int getAdjustedPosition(int position){
        initial.indexOf position
    }
}
