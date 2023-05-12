fun main() {
    val game = Indigo()
    while (true) {
        if(game.input() == 1) return
    }
}

class Indigo {
    fun input(): Int {
        println("Choose an action (reset, shuffle, get, exit):")
        when (readln()) {
            "reset" -> reset()
            "shuffle" -> shuffle()
            "get" -> get()
            "exit" -> return 1
        }
        return 0
    }

    fun reset() {}
    fun shuffle() {}
    fun get() {}
}