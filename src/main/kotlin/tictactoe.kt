fun main() {
    val game = Game()
    game.output()
    while(true) {
        if(game.step() == 1){
            game.output()
            if(game.winnerCheck() == 1) break
        }
    }
}

class Game {
    private var gamer = "X"
    private var gameField = mutableListOf(
        mutableListOf(" ", " ", " "),
        mutableListOf(" ", " ", " "),
        mutableListOf(" ", " ", " ")
    )

    fun output() {
        println("---------")
        for (i in 0..2) {
            for (j in 0..2) {
                if (j == 0) {
                    print("| ")
                }
                print(gameField[i][j])
                if (j == 2) {
                    print(" |")
                    break
                }
                print(" ")
            }
            println()
        }
        println("---------")
    }

    fun step(): Int {
        val cord = readln().split(" ")
        val x: Int
        val y: Int
        try {
            x = cord[0].toInt()
            y = cord[1].toInt()
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
            return 0
        }
        return if (x in 1..3 && y in 1..3) {
            if (gameField[x - 1][y - 1] == " ") {
                gameField[x - 1][y - 1] = gamer
                gamer = if (gamer == "X") "O"
                else "X"
                1
            } else {
                println("This cell is occupied! Choose another one!")
                0
            }
        } else {
            println("Coordinates should be from 1 to 3!")
            0
        }
    }

    fun winnerCheck(): Int {
        var checkVert: String
        var checkHorizon: String
        val checkDiagonalDirectly = gameField[0][0] + gameField[1][1] + gameField[2][2]
        val checkDiagonalBack = gameField[0][2] + gameField[1][1] + gameField[2][0]

        if (checkDiagonalDirectly == "XXX" || checkDiagonalBack == "XXX") {
            println("X wins")
            return 1
        } else if (checkDiagonalDirectly == "OOO" || checkDiagonalBack == "OOO") {
            println("O wins")
            return 1
        }

        for (i in 0..2) {
            checkVert = gameField[0][i] + gameField[1][i] + gameField[2][i]
            checkHorizon = gameField[i].joinToString()
            if (checkHorizon == "X, X, X" || checkVert == "XXX") {
                println("X wins")
                return 1
            } else if (checkHorizon == "O, O, O" || checkVert == "OOO") {
                println("O wins")
                return 1
            }
        }

        return if(gameField[0].contains(" ") || gameField[1].contains(" ") || gameField[2].contains(" ")) {
            0
        } else{
            println("Draw")
            1
        }
    }
}