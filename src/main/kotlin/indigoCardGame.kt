fun main() {
    val game = GameCore()
    println("Indigo Card Game")
    game.intro()
}

class GameCore {
    val player = CardsOnHand(mutableListOf<String>(), mutableListOf<String>(), 0)
    val computer = CardsOnHand(mutableListOf<String>(), mutableListOf<String>(), 0)
    var firstPlayer: String = ""
    val card = Card(player, computer, firstPlayer)

    fun intro() {
        println("Play first?")
        when (readln()) {
            "yes" -> {
                firstPlayer = "Player"
                card.init()
                nextMove("Player")
            }
            "no" -> {
                firstPlayer = "Computer"
                card.init()
                nextMove("Computer")
            }
            "exit" -> return println("Bye!")
            else -> { intro() }
        }
    }

    fun nextMove(turn: String) {
        card.showCards()
        if (gameOver()) return println("Game Over")
        card.giveCards("Player", "Computer")
        when (turn) {
            "Player" -> {
                println("Cards in hand: ${card.digitsToPlayerCards().joinToString().replace(", ", " ")}")
                card.updateTable(readln().toInt(), "Player")
                nextMove("Computer")
            }
            "Computer" -> {
                card.updateTable(0, "Computer")
                nextMove("Player")
            }
        }
    }


    fun gameOver(): Boolean {
        return player.win.size + computer.win.size == 52
    }
}

class Card(val player: CardsOnHand, val computer: CardsOnHand, val firstPlayer: String) {
    var cards = mutableListOf<String>()
    var cardsInTable = mutableListOf<String>()
    val cardsForPoint = mutableListOf("A", "J", "Q", "K", "10")

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
                if (cardsInTable.size > 1) checkWinCards("Player")
            }
            "Computer" -> {
                cardsInTable.add(computer.inHand[card])
                computer.inHand.removeAt(card)
                println("Computer plays ${cardsInTable[cardsInTable.lastIndex]}")
                if (cardsInTable.size > 1) checkWinCards("Computer")
            }
        }
    }

    fun checkWinCards(turn: String) {
        val currentCard = cardsInTable[cardsInTable.lastIndex]
        val previousCard = cardsInTable[cardsInTable.lastIndex - 1]

        val numberCurrent = if (currentCard.length == 3) currentCard[0].toString() + currentCard[1]
        else currentCard[0].toString()
        val numberPrevious = if (previousCard.length == 3) previousCard[0].toString() + previousCard[1]
        else previousCard[0].toString()

        if (numberCurrent == numberPrevious || currentCard[currentCard.lastIndex] == previousCard[previousCard.lastIndex]) {
            when (turn) {
                "Player" -> {
                    for (i in cardsInTable) {
                        if (i[0].toString() in cardsForPoint || i[0].toString() + i[1] in cardsForPoint) player.score++
                    }
                    println("Player wins cards")
                }
                "Computer" -> {
                    for (i in cardsInTable) {
                        if (i[0].toString() in cardsForPoint || i[0].toString() + i[1] in cardsForPoint) computer.score++
                    }
                    println("Computer wins cards")
                }
            }
            giveWinCards(turn)
            showScore()
        }
    }

    fun giveWinCards(turn: String) {
        when (turn) {
            "Player" -> {
                player.win.addAll(cardsInTable)
                cardsInTable.clear()
            }
            "Computer" -> {
                computer.win.addAll(cardsInTable)
                cardsInTable.clear()
            }
        }
        if (player.win.size > computer.win.size) player.score += 3
        else if (player.win.size < computer.win.size) computer.score += 3
        else {
            if (firstPlayer == "Player") player.score += 3
            else computer.score += 3
        }
    }

    fun showScore() {
        println("Score: Player ${player.score} - Computer ${computer.score}")
        println("Cards: Player ${player.win.size} - Computer ${computer.win.size}")
    }

    fun showCards() {
        if (cardsInTable.size == 0) println("No cards on the table")
        else println("${cardsInTable.size} cards on the table, and the top card is ${cardsInTable[cardsInTable.lastIndex]}")
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
        println("Initial cards on the table: ${cardsInTable.joinToString().replace(", ", " ")}")
    }

    fun giveCards(givePlayer: String = "NULL", giveComputer: String = "NULL") {
        if (givePlayer == "Player" && player.inHand.size == 0 && cards.size >= 6) {
            repeat (6) { player.inHand.add(cards[it]) }
            repeat (6) { cards.removeAt(0) }
        }
        if (giveComputer == "Computer" && computer.inHand.size == 0 && cards.size >= 6) {
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