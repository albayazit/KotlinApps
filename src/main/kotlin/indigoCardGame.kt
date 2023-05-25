fun main() {
    val game = GameCore()
    println("Indigo Card Game")
    game.intro()
}

class GameCore {
    val player = CardsOnHand(mutableListOf<String>(), mutableListOf<String>(), 0)
    val computer = CardsOnHand(mutableListOf<String>(), mutableListOf<String>(), 0)
    val card = Card(player, computer)

    fun intro() {
        println("Play first?")
        when (readln()) {
            "yes" -> {
                card.init()
                nextMove("Player")
            }
            "no" -> {
                card.init()
                nextMove("Computer")
            }
            "exit" -> return println("Bye!")
            else -> { intro() }
        }
    }

    fun nextMove(turn: String) {
        card.showCards()
        when (turn) {
            "Player" -> {
                card.showPlayerCards()
                card.updateTable(readln().toInt(), "Player")
                nextMove("Computer")
            }
            "Computer" -> {
                card.updateTable(readln().toInt(), "Computer")
                nextMove("Player")
            }
        }
    }

    fun showScore() {

    }
}

class Card(val player: CardsOnHand, val computer: CardsOnHand) {
    var cards = mutableListOf<String>()
    var cardsInTable = mutableListOf<String>()

    fun init() {
        reset()
        cards.shuffle()
        initTable()
        giveCards("Player", "Computer")
    }

    fun updateTable(card: Int, move: String) {
        when (move) {
            "Player" -> {
                cardsInTable.add(player.inHand[card - 1])
                player.inHand.removeAt(card - 1)
            }
            "Computer" -> {
                cardsInTable.add(computer.inHand[card - 1])
                computer.inHand.removeAt(card - 1)
            }
        }
    }

    fun showCards() {
        if (cardsInTable.size == 0) println("No cards on the table")
        println("${cardsInTable.size} cards on the table, and the top card is ${cardsInTable[cardsInTable.lastIndex]}")
    }

    fun showPlayerCards() {
        println("Cards in hand: ${digitsToPlayerCards().joinToString().replace(", ", " ")}")
        println("Choose a card to play (1-${player.inHand.size}):")
    }

    fun digitsToPlayerCards(): MutableList<String> {
        val result = mutableListOf<String>()
        for (i in player.inHand.indices) {
            result.add("${i + 1})" + player.inHand[i])
        }
        return result
    }

    private fun initTable() {
        repeat (4) { cardsInTable.add(cards[it]) }
        repeat (4) { cards.removeAt(0) }
        println("Initial cards on the table: ${cardsInTable.joinToString().replace(",", " ")}")
    }

    fun giveCards(givePlayer: String = "NULL", giveComputer: String = "NULL") {
        if (givePlayer == "Player") {
            repeat (6) { player.inHand.add(cards[it]) }
            repeat (6) { cards.removeAt(0) }
        }
        if (giveComputer == "Computer") {
            repeat (6) { computer.inHand.add(cards[it]) }
            repeat (6) { cards.removeAt(0) }
        }
    }

    private fun reset() {
        cards = mutableListOf("A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦", "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣")
        cardsInTable.clear()
        player.inHand.clear()
        computer.inHand.clear()
    }
}

data class CardsOnHand(var inHand: MutableList<String>, var win: MutableList<String>, var score: Int)