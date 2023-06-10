import kotlin.random.Random

fun main() {
    val game = GameCore()
    println("Indigo Card Game")
    game.intro()
}

class GameCore {
    private val player = CardsOnHand(mutableListOf(), mutableListOf(), 0)
    private val computer = CardsOnHand(mutableListOf(), mutableListOf(), 0)
    private var whoFirst = WhoFirst()
    private val card = Card(player, computer, whoFirst)

    fun intro() {
        println("Play first?")
        when (readln()) {
            "yes" -> {
                whoFirst.firstPlayer = "Player"
                card.init()
                nextMove("Player")
            }
            "no" -> {
                whoFirst.firstPlayer = "Computer"
                card.init()
                nextMove("Computer")
            }
            "exit" -> return println("Bye")
            else -> { intro() }
        }
    }

    private fun nextMove(turn: String) {
        card.giveCards("Player", "Computer")
        if (player.inHand.size == 0 && computer.inHand.size == 0) {
            card.showCards()
            if(card.cardsInTable.size != 0) card.giveRemainingCards()
            else card.giveLastScore()
        }
        else {
            card.showCards()
            when (turn) {
                "Player" -> {
                    println("Cards in hand: ${card.digitsToPlayerCards().joinToString().replace(", ", " ")}")
                    val action = getCardFromUser()
                    if (action == -1) return println("Game Over")
                    card.updateTable(action, "Player")
                    nextMove("Computer")
                }

                "Computer" -> {
                    println(computer.inHand.joinToString().replace(",", ""))
                    card.updateTable(getCardFromComputer(), "Computer")
                    nextMove("Player")
                }
            }
        }
    }

    private fun getCardFromUser(): Int {
        do {
            println("Choose a card to play (1-${player.inHand.size}):")
            val action = readln()
            try {
                if (action.toInt() in 1..player.inHand.size) return action.toInt()
            } catch (_: Exception) {}
        } while (action != "exit")
        return -1
    }

    private fun getCardFromComputer(): Int {
        val currentCard = card.cardsInTable.last()
        val numberCardInTable = if (currentCard.length == 3) currentCard[0].toString() + currentCard[1]
        else currentCard[0].toString()
        val suitCardInTable = currentCard[currentCard.lastIndex]
        if (computer.inHand.size == 1) return 0
        if (checkIfOneCondidate(numberCardInTable, suitCardInTable) != -1) return checkIfOneCondidate(numberCardInTable, suitCardInTable)
        if (noCardsInTable() != -1) return noCardsInTable()
        if (cardsOnTableWithoutCandidate() != -1) return cardsOnTableWithoutCandidate()
        return 0
    }

    fun cardsOnTableWithoutCandidate(): Int {
        val sameSuits = valueOfSameSuits()
        return -1
    }

    fun valueOfSameSuits() {

    }

    fun noCardsInTable(): Int {
        if (card.cardsInTable.size == 0) {
            val candidateCardsWithSuits = checkSameSuits()
            val candidateCardsWithNumbers = checkSameNumbers()
            return if (candidateCardsWithSuits != -1) candidateCardsWithSuits
            else if (candidateCardsWithNumbers != -1) candidateCardsWithNumbers
            else Random.nextInt(0, computer.inHand.lastIndex)
        }
        return -1
    }
    fun checkSameNumbers(): Int {
        var index = 0
        var candidateCards = mutableListOf<Int>()
        var cards = mutableListOf<String>()
        for (i in computer.inHand) {
            val numberCardInHand = if (i.length == 3) i[0].toString() + i[1]
            else i[0].toString()
            if (numberCardInHand in cards) candidateCards.add(index)
            else cards.add(numberCardInHand)
            index++
        }
        return if (candidateCards.size != 0) Random.nextInt(candidateCards.lastIndex)
        else -1
    }
    fun checkSameSuits(): Int {
        var index = 0
        var candidateCards = mutableListOf<Int>()
        var cards = mutableListOf<Char>()
        for (i in computer.inHand) {
            val suitCardInHand = i[i.lastIndex]
            if (suitCardInHand in cards) candidateCards.add(index)
            else cards.add(suitCardInHand)
            index++
        }
        return if (candidateCards.size != 0) Random.nextInt(candidateCards.lastIndex)
        else -1
    }

    fun checkIfOneCondidate(number: String, suit: Char): Int {
        var count = 0
        var cardToReturn = 0
        var index = 0

        for (i in computer.inHand) {
            val numberCardInHand = if (i.length == 3) i[0].toString() + i[1]
            else i[0].toString()
            val suitCardInHand = i[i.lastIndex]
            if (numberCardInHand == number || suitCardInHand == suit) {
                cardToReturn = index
                count++
            }
            index++
        }
        if (count == 1) return cardToReturn
        return -1
    }

    fun getCondidateCard() {

    }
}

class Card(private val player: CardsOnHand, private val computer: CardsOnHand, private val whoFirst: WhoFirst) {
    private var cards = mutableListOf<String>()
    var cardsInTable = mutableListOf<String>()
    private val cardsForPoint = mutableListOf("A", "J", "Q", "K", "10")

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

    private fun checkWinCards(turn: String) {
        var score = 0
        val currentCard = cardsInTable[cardsInTable.lastIndex]
        val previousCard = cardsInTable[cardsInTable.lastIndex - 1]
        val numberCurrent = if (currentCard.length == 3) currentCard[0].toString() + currentCard[1]
        else currentCard[0].toString()
        val numberPrevious = if (previousCard.length == 3) previousCard[0].toString() + previousCard[1]
        else previousCard[0].toString()
        if (numberCurrent == numberPrevious || currentCard[currentCard.lastIndex] == previousCard[previousCard.lastIndex]) {
            for (i in cardsInTable) {
                if (i.replaceFirst(".$".toRegex(), "") in cardsForPoint) score++
            }
            when (turn) {
                "Player" -> {
                    player.score += score
                    whoFirst.lastWinner = "Player"
                    println("Player wins cards")
                }
                "Computer" -> {
                    computer.score += score
                    whoFirst.lastWinner = "Computer"
                    println("Computer wins cards")
                }
            }
            giveWinCards(turn)
            showScore()
        }
    }

    private fun giveWinCards(turn: String) {
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
    }

    private fun showScore() {
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

    fun giveRemainingCards() {
        var score = 0
        for (i in cardsInTable) {
            if (i.replaceFirst(".$".toRegex(), "") in cardsForPoint) score++
        }
        when (whoFirst.lastWinner) {
            "Player" -> {
                player.score += score
                giveWinCards("Player")
            }
            "Computer" -> {
                computer.score += score
                giveWinCards("Computer")
            }
            else -> {
                if (whoFirst.firstPlayer == "Player") player.score += score
                else computer.score += score
                giveWinCards(whoFirst.firstPlayer)
            }
        }
        giveLastScore()
    }

    fun giveLastScore() {
        if (player.win.size > computer.win.size) player.score += 3
        else if (player.win.size < computer.win.size) computer.score += 3
        else {
            when (whoFirst.firstPlayer) {
                "Player" -> player.score += 3
                "Computer" -> computer.score += 3
            }
        }
        showScore()
        println("Game Over")
    }

    private fun reset() {
        cards = mutableListOf("A♠", "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠", "A♥", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "Q♥", "K♥", "A♦", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "Q♦", "K♦", "A♣", "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣")
        cardsInTable.clear()
        player.inHand.clear()
        computer.inHand.clear()
    }
}

data class CardsOnHand(var inHand: MutableList<String>, var win: MutableList<String>, var score: Int)
data class WhoFirst(var firstPlayer: String = "NULL", var lastWinner: String = "NULL")