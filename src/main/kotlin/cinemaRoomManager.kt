import java.util.ArrayList

fun main() {
    val cinema = Cinema()
    cinema.input()
    cinema.math()
//    cinema.field()
    cinema.output()
//    cinema.outputField()
}

class Cinema {
    private var rows = 0
    private var seats = 0
    private var earn = 0
    private var field = mutableListOf<MutableList<String>>()


    fun input() {
        println("Enter the number of rows:")
        rows = readln().toInt()
        println("Enter the number of seats in each row:")
        seats = readln().toInt()
    }

    fun math() {
        if(rows * seats <= 60) {
            earn = rows * seats * 10
        }
        else {
            for(i in 0 until rows) {
                if(i in 0 until rows / 2) earn += seats * 10
                else earn += seats * 8
            }
        }
    }

    fun field() {
        repeat(rows) { field.add(mutableListOf()) }
        for(i in 0 until rows) {
            repeat(seats) { field[i].add("  ") }
        }
        println("Cinema:")
        for (i in 0 until rows) {
            for (j in 0 until seats) {
                if(i == 0 && j != 0) {
                    field[i][j] = " $j"
                }
                field[i][0] = i.toString()
                field[0][0] = " "
            }
        }
    }

    fun output() {
        println("Total income:\n$$earn")
    }

    fun outputField() {
        for(i in 0 until rows) {
            for(j in 0 until seats) {
                print(field[i][j])
            }
            println()
        }
    }
}