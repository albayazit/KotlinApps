import java.lang.Exception

fun main() {
    val cinema = Cinema()
    cinema.input()
    cinema.fieldInit()
    while(true) {
        if(cinema.menu() == 1) break
    }
}

class Cinema {
    private var rows = 0
    private var seats = 0
    private var price = 0
    private var purchased = 0
    private var earn = 0
    private var field = mutableListOf<MutableList<String>>()


    fun input() {
        println("Enter the number of rows:")
        rows = readln().toInt()
        println("Enter the number of seats in each row:")
        seats = readln().toInt()
    }

    fun menu(): Int {
        println("""
            1. Show the seats
            2. Buy a ticket
            3. Statistics
            0. Exit
        """.trimIndent())
        when (readln().toInt()) {
            1 -> outputField()
            2 -> buy()
            3 -> statistics()
            0 -> return 1
            else -> {
                println("Wrong input!")
                menu()
            }
        }
        return 0
    }

    private fun statistics() {
        val percent = purchased.toDouble() / (rows * seats) * 100.0
        val percentage = "%.2f".format(percent)

        println("Number of purchased tickets: $purchased")
        println("Percentage: $percentage%")
        println("Current income: $$earn")
        var income = 0
        if(rows * seats <= 60) {
            income = 10 * rows * seats
        } else {
            for(i in 0 until rows) {
                income += if(i in 0 until rows / 2) 10 * seats
                else 8  * seats
            }
        }
        println("Total income: $$income")
    }

    private fun buy() {
        println("Enter a row number:")
        val row: Int = readln().toInt()
        println("Enter a seat number in that row:")
        val seat: Int = readln().toInt()

        try {
            if(field[row][seat] == " B") {
                println("That ticket has already been purchased!")
                buy()
            }
            else field[row][seat] = " B"
        } catch (e: Exception) {
            return println("Wrong input!")
        }

        price = if(rows * seats <= 60) {
            10
        } else {
            if(row in 0 .. rows / 2) 10
            else 8
        }
        earn += price
        purchased += 1
        println("Ticket price: $$price")
    }

    fun fieldInit() {
        repeat(rows + 1) { field.add(mutableListOf()) }
        for(i in 0 .. rows) {
            repeat(seats + 1) { field[i].add("  ") }
        }
        for (i in 0 .. rows) {
            for (j in 0 .. seats) {
                field[i][j] = " S"
                if(i == 0 && j != 0) {
                    field[i][j] = " $j"
                }
                field[i][0] = i.toString()
                field[0][0] = " "
            }
        }
    }

    private fun outputField() {
        println("Cinema:")
        for(i in 0 .. rows) {
            for(j in 0 .. seats) {
                print(field[i][j])
            }
            println()
        }
    }
}