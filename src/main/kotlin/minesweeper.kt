import java.lang.Exception
import kotlin.random.Random

fun main() {
    val game = MineSweeper()
    game.inputMines()
    game.fieldGen()
    game.minesGen()
    while(true) {
        if(game.emptyCheck() == 0) break
        else game.clearMass()
    }
    game.output()
    while (true) {
        if(game.inputSet() == 1) {
            game.output()
            return println("You stepped on a mine and failed!")
        }
        game.output()
        if(game.overCheckMine() == 1 || game.overCheckMark() == 1) return println("Congratulations! You found all the mines!")
    }
}

class MineSweeper {
    private var field = mutableListOf(mutableListOf<String>())
    private var fieldMain = mutableListOf(mutableListOf<String>())
    private val cells = mutableListOf<MutableList<Int>>()
    private val cellsAll = mutableListOf<MutableList<Int>>()
    private var mineCount = 0
    private var firstMove = true

    fun inputMines() {
        println("How many mines do you want on the field?")
        mineCount = readln().toInt()
    }

    fun inputSet(): Int {
        println("Set/unset mines marks or claim a cell as free:")
        val data = readln().split(" ")
        val cordY = data[0].toInt() - 1
        val cordX = data[1].toInt() - 1
        when (data[2]) {
            "free" -> if(freeField(cordX, cordY) == 1) return 1
            "mine" -> markMine(cordX, cordY)
        }
        firstMove = false
        return 0
    }

    fun emptyCheck(): Int {
        for(i in 0..8) {
            for (j in 0..8) {
                if(field[i][j] == "/") {
                    if(emptyCheckReplace(i, j) == 1) return 1
                }
            }
        }
        return 0
    }

    private fun emptyCheckReplace(x: Int, y: Int): Int {
        if (x < 8 && y < 8 && field[x + 1][y + 1] == "/" && field[x + 1][y] != "/" && field[x][y + 1] != "/") return 1
        if (x > 0 && y > 0 && field[x - 1][y - 1] == "/" && field[x - 1][y] != "/" && field[x][y - 1] != "/") return 1
        if (x < 8 && y > 0 && field[x + 1][y - 1] == "/" && field[x + 1][y] != "/" && field[x][y - 1] != "/") return 1
        if (x > 0 && y < 8 && field[x - 1][y + 1] == "/" && field[x - 1][y] != "/" && field[x][y + 1] != "/") return 1
        return 0
    }

    private fun markMine(x: Int, y: Int) {
        if(fieldMain[x][y] == "*") fieldMain[x][y] = "."
        else if(fieldMain[x][y] == ".") fieldMain[x][y] = "*"
    }

    fun overCheckMine() : Int{
        for(i in 0..8) {
            if(fieldMain[i].joinToString("") != field[i].joinToString("").replace("X", ".")) return 0
        }
        return 1
    }

    fun overCheckMark(): Int {
        var haveMark = false
        for(i in 0..8) {
            for(j in 0..8) {
                if(fieldMain[i][j] == "*") {
                    haveMark = true
                    if(field[i][j] != "X") return 0
                }
                if (field[i][j] == "X" && fieldMain[i][j] != "*") return 0
            }
        }
        return if(!haveMark) 0
        else 1
    }

    private fun freeField(cordX: Int, cordY: Int): Int {
        if(field[cordX][cordY] == "X"){
           if(!firstMove) {
               openMines()
               return 1
           }
           rearrangeMine(cordX, cordY)
        } else if (field[cordX][cordY] == "/") {
            fieldMain[cordX][cordY] = field[cordX][cordY]
            freeFieldOpen(cordX, cordY)
        } else {
            fieldMain[cordX][cordY] = field[cordX][cordY]
        }
        return 0
    }

    fun clearMass() {
        field = mutableListOf(mutableListOf())
        fieldMain = mutableListOf(mutableListOf())
        fieldGen()
        minesGen()
    }

    private fun rearrangeMine(x: Int, y: Int) {
        do {
            clearMass()
            if(emptyCheck() == 0) break
            else rearrangeMine(x, y)
        } while (field[x][y] != "/")
        freeField(x, y)
    }

    private fun openMines() {
        for (i in 0..8) {
            for (j in 0..8) {
                if(field[i][j] == "X") fieldMain[i][j] = "X"
            }
        }
    }

    private fun freeFieldOpen(cordX: Int, cordY: Int) {
        cells.add(mutableListOf(cordX, cordY))
        while(cells.size > 0) {
            val x = cells[0][0]
            val y = cells[0][1]
            fieldMain[x][y] = field[x][y]
            if(field[x][y] != "/") {
                cellsAll.add(mutableListOf(x, y))
                cells.remove(mutableListOf(x, y))
                continue
            } else {
                checkNeigh(x, y)
            }
            cellsAll.add(mutableListOf(x, y))
            cells.remove(mutableListOf(x, y))
        }
    }
    private fun checkNeigh(x: Int, y: Int) {
        if (x < 8 && mutableListOf(x + 1, y) !in cellsAll && mutableListOf(x + 1, y) !in cells) cells.add(mutableListOf(x + 1, y))
        if (x > 0 && mutableListOf(x - 1, y) !in cellsAll && mutableListOf(x - 1, y) !in cells) cells.add(mutableListOf(x - 1, y))
        if (y < 8 && mutableListOf(x, y + 1) !in cellsAll && mutableListOf(x, y + 1) !in cells) cells.add(mutableListOf(x, y + 1))
        if (y > 0 && mutableListOf(x, y - 1) !in cellsAll && mutableListOf(x, y - 1) !in cells) cells.add(mutableListOf(x, y - 1))
        if (x < 8 && y < 8 && field[x + 1][y] != "/" && field[x][y + 1] != "/") fieldMain[x + 1][y + 1] = field[x + 1][y + 1]
        if (x > 0 && y > 0 && field[x - 1][y] != "/" && field[x][y - 1] != "/") fieldMain[x - 1][y - 1] = field[x - 1][y - 1]
        if (x < 8 && y > 0 && field[x + 1][y] != "/" && field[x][y - 1] != "/") fieldMain[x + 1][y - 1] = field[x + 1][y - 1]
        if (x > 0 && y < 8 && field[x - 1][y] != "/" && field[x][y + 1] != "/") fieldMain[x - 1][y + 1] = field[x - 1][y + 1]
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
            do {
                mineX = Random.nextInt(0, 9)
                mineY = Random.nextInt(0, 9)
            } while (field[mineX][mineY] == "X")
            field[mineX][mineY] = "X"
            minesGenSetMine(mineX, mineY)
        }

    }

    private fun minesGenSetMine(mineX: Int, mineY: Int) {
        for(i in -1..1) {
            for(j in -1..1) {
                try {
                    field[mineX + i][mineY + j] = (if (field[mineX + i][mineY + j] == "/") {
                        1
                    } else {
                        field[mineX + i][mineY + j].toInt() + 1
                    }).toString()
                } catch (e: Exception) {
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