import kotlin.random.Random

fun main() {
    dataRead()
}
fun dataRead() {
    print("How many mines do you want on the field? > ")
    val mineCount = readln().toInt()
    fieldMine(gameField(), mineCount)
}

fun gameField(): MutableList<MutableList<String>> {
    var gameField = mutableListOf(
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
    return gameField
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
    fieldMines(gameField)
}

fun fieldMines(gameField: MutableList<MutableList<String>>) {
    for (i in 0..8) {
        for(j in 0..8){
            if(gameField[i][j] == ".") {
                mineCheck(gameField, i, j)
            }
        }
    }
    gameOut(gameField)
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


fun gameOut(gameField: MutableList<MutableList<String>> ) {
    println(" │123456789│\n—│—————————│")
    for(i in gameField.indices) {
        println("${i + 1}|" + gameField[i].joinToString("") + "|")
    }
    println("—│—————————│")
}