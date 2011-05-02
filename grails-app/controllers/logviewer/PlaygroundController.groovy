package logviewer

class PlaygroundController {

    def mongo

    def index = {
        render text: mongo.inspect()
    }
}
