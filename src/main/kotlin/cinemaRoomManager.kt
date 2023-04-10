fun main() {
    val cinema = Cinema()
    cinema.input()
    cinema.fieldInit()
    cinema.outputField()
    cinema.buy()
    cinema.math()
    cinema.output()
    cinema.outputField()
}

class Cinema {
    private var rows = 0
    private var seats = 0
    private var earn = 0
    private var price = 0
    private var field = mutableListOf<MutableList<String>>()


    fun input() {
        println("Enter the number of rows:")
        rows = readln().toInt()
        println("Enter the number of seats in each row:")
        seats = readln().toInt()
    }

    fun buy() {
        println("Enter a row number:")
        val row: Int = readln().toInt()
        println("Enter a seat number in that row:")
        val seat: Int = readln().toInt()

        field[row][seat] = " B"

        price = if(rows * seats <= 60) {
            10
        } else {
            if(row in 0 .. rows / 2) 10
            else 8
        }
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

    fun math() {
        if(rows * seats <= 60) {
            earn = rows * seats * 10
        }
        else {
            for(i in 0 .. rows) {
                earn += if(i in 0 .. rows / 2) seats * 10
                else seats * 8
            }
        }
    }

    fun output() {
//        println("Total income:\n$$earn")
        println("Ticket price: $$price")
    }

    fun outputField() {
        println("Cinema:")
        for(i in 0 .. rows) {
            for(j in 0 .. seats) {
                print(field[i][j])
            }
            println()
        }
    }
}