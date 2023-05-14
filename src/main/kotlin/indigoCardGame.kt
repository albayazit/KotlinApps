fun main() {
    val game = IndigoGame()
    println("Indigo Card Game")
    game.intro()
}

class Card {
    var cards = mutableListOf("A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦", "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣")
    var cardsInTable = mutableListOf<String>()

    fun resetCards() {
        cards = mutableListOf("A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦", "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣")
        println("Card deck is reset.")
    }
    fun shuffleCards() {
        cards.shuffle()
//        println("Card deck is shuffled.")
    }
    fun getCards() {
        cardsInTable.add(cards[cards.lastIndex - 3])
        cardsInTable.add(cards[cards.lastIndex - 2])
        cardsInTable.add(cards[cards.lastIndex - 1])
        cardsInTable.add(cards[cards.lastIndex])
    }
}

class IndigoGame {
    val game = Card()

    fun intro() {
        game.shuffleCards()
        println("Play first?")
        val action = readln()
        if (action.uppercase() == "YES") initialCards()
        else if (action.uppercase() == "NO") initialCards()
        else intro()
    }

    private fun initialCards() {
        game.getCards()
        println(game.cardsInTable.joinToString().replace(", ", " "))
    }
}