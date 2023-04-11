import kotlin.random.Random

fun main() {
    val field = gameField()
    val mineCount = dataRead()
    fieldMine(field, mineCount)
    fieldMines(field)
    val fieldClone = hideMine(field)
    while (true) {
        if (gameOver(field, fieldClone) == 1) return println("Congratulations! You found all the mines!")
        gameOut(field)
        setMine(field)
    }
}
fun dataRead(): Int {
    print("How many mines do you want on the field? > ")
    return readln().toInt()
}

fun gameField(): MutableList<MutableList<String>> {
    return mutableListOf(
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".")
    )
}

fun fieldMine(gameField: MutableList<MutableList<String>>, mineCount: Int) {
    for (i in 0 until mineCount) {
        var randCount = Random.nextInt(0, 9)
        var randCountTwo = Random.nextInt(0, 9)
        while (gameField[randCount][randCountTwo] == "X") {
            randCount = Random.nextInt(0, 9)
            randCountTwo = Random.nextInt(0, 9)
        }
        gameField[randCount][randCountTwo] = "X"
    }
}

fun fieldMines(gameField: MutableList<MutableList<String>>) {
    for (i in 0..8) {
        for(j in 0..8){
            if(gameField[i][j] == ".") {
                mineCheck(gameField, i, j)
            }
        }
    }
}

fun mineCheck(gameField: MutableList<MutableList<String>>, i: Int, j: Int) {
    var sum = 0
    for (k in -1..1) {
        for (n in -1..1) {
            try {
                if (gameField[i + k][j + n] == "X") sum += 1
            }
            finally {
                continue
            }
        }
    }
    if(sum != 0) gameField[i][j] = sum.toString()
    else gameField[i][j] = "."
}

fun hideMine(gameField: MutableList<MutableList<String>>): MutableList<MutableList<String>> {
    val mines = gameField()
    for (i in 0..8) {
        for(j in 0..8) {
            mines[i][j] = gameField[i][j]
        }
    }
    for (i in 0..8) {
        for(j in 0..8){
            if(gameField[i][j] == "X") {
                gameField[i][j] = "."
                mines[i][j] = "*"
            }
        }
    }
    return mines
}


fun gameOut(gameField: MutableList<MutableList<String>>) {
    println(" │123456789│\n—│—————————│")
    for(i in gameField.indices) {
        println("${i + 1}|" + gameField[i].joinToString("") + "|")
    }
    println("—│—————————│")
}

fun gameOver(gameField: MutableList<MutableList<String>>, mines: MutableList<MutableList<String>>): Int {
    for(i in 0..8) {
        for(j in 0..8) {
            if(mines[i][j] != gameField[i][j]) {
                return 0
            }
        }
    }
    return 1
}

fun setMine(gameField: MutableList<MutableList<String>>) {
    println("Set/delete mines marks (x and y coordinates):")
    val cord = readln().split(" ")
    val cordX = cord[0].toInt() - 1
    val cordY = cord[1].toInt() - 1
    if(gameField[cordX][cordY] == "*") {
        gameField[cordX][cordY] = "."
    } else if(gameField[cordX][cordY] == "."){
        gameField[cordX][cordY] = "*"
    } else {
        println("There is a number here!")
        setMine(gameField)
    }
}