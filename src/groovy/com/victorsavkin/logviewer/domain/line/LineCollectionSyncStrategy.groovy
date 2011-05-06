package com.victorsavkin.logviewer.domain.line

interface LineCollectionSyncStrategy {
    
    List<LineCollection> sync(List<LineCollection> collections)
}