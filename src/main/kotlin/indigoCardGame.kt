fun main() {
    val game = IndigoGame()
    println("Indigo Card Game")
    game.intro()
}

class Card {
    var cards = mutableListOf("A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦", "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣")
    var cardsInTable = mutableListOf<String>()
    var cardsInHandPlayer = mutableListOf<String>()
    var cardsInHandPC = mutableListOf<String>()
    var winCardsPlayer = mutableListOf<String>()
    var winCardsPC = mutableListOf<String>()

    fun shuffleCards() {
        cards.shuffle()
    }
    fun getCards() {
        repeat (4) {
            cardsInTable.add(cards[it])
        }
        repeat (4) {
            cards.removeAt(0)
        }
    }

    fun giveCardsPlayer() {
        repeat (6) {
            cardsInHandPlayer.add(cards[it])
        }
        repeat (6) {
            cards.removeAt(0)
        }
    }

    fun giveCardsPC() {
        repeat (6) {
            cardsInHandPC.add(cards[it])
        }
        repeat (6) {
            cards.removeAt(0)
        }
    }

    fun printPlayerCards(): MutableList<String> {
        val result = mutableListOf<String>()
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
            game.getCards()
            game.giveCardsPlayer()
            game.giveCardsPC()
            println("Initial cards on the table: ${game.cardsInTable.joinToString().replace(", ", " ")}")
            initialCards("PLAYER")
        }
        else if (action.uppercase() == "NO") {
            game.shuffleCards()
            game.getCards()
            game.giveCardsPlayer()
            game.giveCardsPC()
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
                if (game.cardsInHandPlayer.isEmpty() && game.cards.size != 0) game.giveCardsPlayer()
                println("Cards in hand: ${game.printPlayerCards().joinToString().replace(",", "")}")
                nextMove(turn)
            }
            "PC" -> {
                if (game.cardsInHandPlayer.isEmpty() && game.cards.size != 0) game.giveCardsPC()
                nextMove(turn)
            }
        }
    }

    private fun nextMove(turn: String) {
        val action: String
        when (turn) {
            "PLAYER" -> {
                println("Choose a card to play (1-${game.cardsInHandPlayer.size}):")
                action = readln()
                if (action == "exit") return println("Game Over")
                try {
                    action.toInt()
                    winCardsPlayer(action.toInt())
                    game.cardsInTable.add(game.cardsInHandPlayer[action.toInt() - 1])
                    game.cardsInHandPlayer.removeAt(action.toInt() - 1)
                    initialCards("PC")
                }
                catch (e: Exception) {nextMove(turn)}
            }
            "PC" -> {
                println("Computer plays ${game.cardsInHandPC[0]}")
                game.cardsInTable.add(game.cardsInHandPC[0])
                game.cardsInHandPC.removeAt(0)
                winCardsPC()
                initialCards("PLAYER")
            }
        }
        return
    }

    private fun gameOver(): Boolean {
        return game.cardsInTable.size == 52
    }

    private fun winCardsPlayer(action: Int = 1) {
        if (game.cardsInHandPlayer[action - 1].length == 3) {
            if ((game.cardsInHandPlayer[action - 1][0] == game.cardsInTable[game.cardsInTable.lastIndex][0] &&
                game.cardsInHandPlayer[action - 1][1] == game.cardsInTable[game.cardsInTable.lastIndex][1]) ||
                game.cardsInHandPlayer[action - 1][2] == game.cardsInTable[game.cardsInTable.lastIndex][2]) {
                    println("Player wins cards")
                    game.winCardsPlayer.addAll(game.cardsInTable)
                    game.cardsInTable.clear()
            }
        }
        else {
            if (game.cardsInHandPlayer[action - 1][0] == game.cardsInTable[game.cardsInTable.lastIndex][0] ||
                game.cardsInHandPlayer[action - 1][1] == game.cardsInTable[game.cardsInTable.lastIndex][1]) {
                    println("Player wins cards")
                    game.winCardsPlayer.addAll(game.cardsInTable)
                    game.cardsInTable.clear()
            }
        }
    }

    private fun winCardsPC() {
        if (game.cardsInHandPC[0].length == 3) {
            if ((game.cardsInHandPC[0][0] == game.cardsInTable[game.cardsInTable.lastIndex][0] &&
                game.cardsInHandPC[0][1] == game.cardsInTable[game.cardsInTable.lastIndex][1]) ||
                game.cardsInHandPC[0][2] == game.cardsInTable[game.cardsInTable.lastIndex][2]) {
                    println("Computer wins cards")
                    game.winCardsPC.addAll(game.cardsInTable)
                    game.cardsInTable.clear()
            }
        }
        else {
            if (game.cardsInHandPC[0][0] == game.cardsInTable[game.cardsInTable.lastIndex][0] ||
                game.cardsInHandPC[0][1] == game.cardsInTable[game.cardsInTable.lastIndex][1]) {
                    println("Computer wins cards")
                    game.winCardsPlayer.addAll(game.cardsInTable)
                    game.cardsInTable.clear()
            }
        }
    }

    private fun showScore() {

    }

    private fun showCards() {}
}