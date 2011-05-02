grails {
    mongo {
        host = "localhost"
        port = 27017
        username = ""
        password = ""
    }
}

environments {
    development {
        grails.mongo.databaseName = "logviewer-dev"
    }

    test {
        grails.mongo.databaseName = "logviewer-test"
    }
    
    production {
    }
}
