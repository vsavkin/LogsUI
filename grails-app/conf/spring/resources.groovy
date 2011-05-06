// Place your Spring DSL code here
beans = {
    htmlGenerator(com.victorsavkin.logviewer.view.HtmlGenerator)
    syncStrategy(com.victorsavkin.logviewer.domain.line.DateBasedLineCollectionSyncStrategy)
}
