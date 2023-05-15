package indigo

fun main() {
    val game = IndigoGame()
    println("Indigo indigo.Card Game")
    game.intro()
}

class Card {
    var cards = mutableListOf("A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦", "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣")
    var cardsInTable = mutableListOf<String>()
    var cardsInHandPlayer = mutableListOf<String>()
    var cardsInHandPC = mutableListOf<String>()

    fun resetCards() {
        cards = mutableListOf("A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦", "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣")
        println("indigo.Card deck is reset.")
    }
    fun shuffleCards() {
        cards.shuffle()
    }
    fun getCards() {
        repeat (4) {
            cardsInTable.add(cards[cards.lastIndex - it])
            cards.remove(cards[cards.lastIndex - it])
        }
    }

    fun giveCards() {
        repeat (6) {
            cardsInHandPlayer.add(cards[cards.lastIndex - it])
            cards.remove(cards[cards.lastIndex - it])
        }
        repeat (6) {
            cardsInHandPC.add(cards[cards.lastIndex - it])
            cards.remove(cards[cards.lastIndex - it])
        }
    }

    fun printPlayerCards(): MutableList<String> {
        var result = mutableListOf<String>()
        for (i in cardsInHandPlayer.indices) {
            result.add("${i + 1})" + cardsInHandPlayer[i])
        }
        return result
    }
}

class IndigoGame {
    private val game = Card()

    fun intro() {
        println("Play first?")
        val action = readln()
        if (action.uppercase() == "YES") {
            game.shuffleCards()
            game.giveCards()
            game.getCards()
            println("Initial cards on the table: ${game.cardsInTable.joinToString().replace(", ", " ")}")
            initialCards("PLAYER")
        }
        else if (action.uppercase() == "NO") {
            game.shuffleCards()
            game.giveCards()
            game.getCards()
            println("Initial cards on the table: ${game.cardsInTable.joinToString().replace(", ", " ")}")
            initialCards("PC")
        }
        else intro()
    }

    private fun initialCards(turn: String) {
        println("${game.cardsInTable.size} cards on the table, and the top card is ${game.cardsInTable[game.cardsInTable.lastIndex]}")
        if (gameOver()) return println("Game Over")
        when (turn) {
            "PLAYER" -> {
                nextMove(turn)
            }
            "PC" -> {
                nextMove(turn)
            }
        }
    }

    private fun nextMove(turn: String) {
        when (turn) {
            "PLAYER" -> {
                println("Cards in hand: ${game.printPlayerCards().joinToString().replace(",", "")}")
                println("Choose a card to play (1-${game.cardsInHandPlayer.size}):")
                val action = readln().toIntOrNull() ?: nextMove(turn)
                if (action !in 1 .. game.cardsInHandPlayer.size) nextMove(turn)
                else if (action == "exit") return println("Game Over")
                game.cardsInTable.add(game.cardsInHandPlayer[action.toString().toInt() - 1])
                game.cardsInHandPlayer.removeAt(action.toString().toInt() - 1)
                initialCards("PC")
            }
            "PC" -> {
                println("Computer plays ${game.cardsInHandPC[0]}")
                game.cardsInTable.add(game.cardsInHandPC[0])
                game.cardsInHandPC.removeAt(0)
                initialCards("PLAYER")
            }
        }
    }

    private fun gameOver(): Boolean {
        return game.cardsInTable.size == 52
    }
}