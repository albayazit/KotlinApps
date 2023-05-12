fun main() {
    val game = Indigo()
    while (true) {
        if(game.input() == 1) return
    }
}

//println("A 2 3 4 5 6 7 8 9 10 J Q K")
//println("♦ ♥ ♠ ♣")
//println("A♠ 2♠ 3♠ 4♠ 5♠ 6♠ 7♠ 8♠ 9♠ 10♠ J♠ Q♠ K♠ A♥ 2♥ 3♥ 4♥ 5♥ 6♥ 7♥ 8♥ 9♥ 10♥ J♥ Q♥ K♥ A♦ 2♦ 3♦ 4♦ 5♦ 6♦ 7♦ 8♦ 9♦ 10♦ J♦ Q♦ K♦ A♣ 2♣ 3♣ 4♣ 5♣ 6♣ 7♣ 8♣ 9♣ 10♣ J♣ Q♣ K♣"

class Indigo {
    var cards = mutableListOf<String>()

    fun input(): Int {
        println("Choose an action (reset, shuffle, get, exit):")
        when (readln()) {
            "reset" -> reset()
            "shuffle" -> shuffle()
            "get" -> get()
            "exit" -> {
                println("Bye")
                return 1
            }
            else -> println("Wrong action.")
        }
        return 0
    }

    fun reset() {
        cards = mutableListOf("A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦", "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣")
        println("Card deck is reset.")
    }
    fun shuffle() {
        cards.shuffle()
        println("Card deck is shuffled.")
    }
    fun get() {}
}