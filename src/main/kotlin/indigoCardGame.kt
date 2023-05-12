fun main() {
    val game = IndigoGame()
    while (true) {
        if(game.input() == 1) break
    }
}

class IndigoGame {
    private var cards = mutableListOf("A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦", "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣")

    fun input(): Int {
        println("Choose an action (reset, shuffle, get, exit):")
        when (readln()) {
            "reset" -> resetCards()
            "shuffle" -> shuffleCards()
            "get" -> getCards()
            "exit" -> {
                println("Bye")
                return 1
            }
            else -> println("Wrong action.")
        }
        return 0
    }

    private fun resetCards() {
        cards = mutableListOf("A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦", "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣")
        println("Card deck is reset.")
    }
    private fun shuffleCards() {
        cards.shuffle()
        println("Card deck is shuffled.")
    }
    private fun getCards() {
        println("Number of cards:")
        val number = readln().toIntOrNull() ?: return println("Invalid number of cards.")
        if(number !in 1..52) return println("Invalid number of cards.")
        if(number > cards.size) return println("The remaining cards are insufficient to meet the request.")
        repeat (number) {
            print("${cards[cards.lastIndex]} ")
            cards.removeAt(cards.lastIndex)
        }
        println()
    }
}