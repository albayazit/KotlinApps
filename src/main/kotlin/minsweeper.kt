import java.beans.Expression
import java.lang.Exception
import kotlin.random.Random

fun main() {
    val game = MineSweeper()
    game.inputMines()
    game.fieldGen()
    game.minesGen()
    game.output()
    while (true) {
        game.inputSet()
        game.output()
    }
}

class MineSweeper {
    private val field = mutableListOf(mutableListOf<String>())
    private val fieldMain = mutableListOf(mutableListOf<String>())
    private val fieldHelper = mutableListOf(mutableListOf<String>())
    private var mineCount = 0

    fun inputMines() {
        println("How many mines do you want on the field?")
        mineCount = readln().toInt()
    }

    fun inputSet() {
        println("Set/unset mines marks or claim a cell as free:")
        val data = readln().split(" ")
        val cordX = data[0].toInt() - 1
        val cordY = data[1].toInt() - 1
        val action = data[2]
        when (action) {
            "free" -> freeField(cordX, cordY)
        }
    }

    fun freeField(cordX: Int, cordY: Int) {
        if(field[cordY][cordX] == "X") return println("GG")
        else {
            freeEmpty(cordX, cordY)
        }
    }

    fun freeEmpty(cordX: Int, cordY: Int) {
        if(field[cordY][cordX] != "/") fieldMain[cordY][cordX] = field[cordY][cordX]
    }

    fun fieldGen() {
        for(i in 0..8) {
            for(j in 0..8) {
                field[i].add("/")
                fieldMain[i].add(".")
            }
            field.add(mutableListOf())
            fieldMain.add(mutableListOf())
        }
    }

    fun minesGen() {

        repeat(mineCount) {
            var mineX: Int
            var mineY: Int
            do{
                mineX = Random.nextInt(0, 9)
                mineY = Random.nextInt(0, 9)
            } while (field[mineX][mineY] == "X")
            field[mineX][mineY] = "X"
            minesGenSetMine(mineX, mineY)
        }

    }
    fun minesGenSetMine(mineX: Int, mineY: Int) {
        for(i in -1..1) {
            for(j in -1..1) {
                try {
                    field[mineX + i][mineY + j] = (if (field[mineX + i][mineY + j] == "/") {
                        1
                    } else {
                        field[mineX + i][mineY + j].toInt() + 1
                    }).toString()
                }
                catch (e: Exception) {
                    continue
                }
            }
        }
    }

    fun output() {
        println(" │123456789│\n—│—————————│")
        for(i in 0..8) {
            println("${i + 1}|${fieldMain[i].joinToString("")}|")
        }
        println("—│—————————│")
    }
}
