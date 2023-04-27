import javax.print.DocFlavor.STRING

fun main() {
    val park = Parking()
    while(true) {
        if(park.input() == 1) break
    }
}

class Parking {
    lateinit var data: List<String>
    fun input(): Int {
        data = readln().split(' ')
        when(data[0]) {
            "park" -> park()
            "leave" -> leave()
            "exit" -> return 1
        }
        return 0
    }

    fun park() {
        val color = data[2]
        for(i in DATABASE.values()) {
            if(i.status == "FREE") {
                println("$color car parked in spot ${i.ordinal + 1}.")
                i.status = "BUSY"
                break
            }
        }
    }

    fun leave() {
        val spot = data[1].toInt()
        when (spot) {
            1 -> {
                if(DATABASE.SPOT1.status == "FREE") {
                    println("There is no car in spot 1.")
                }
                else {
                    println("Spot 1 is free.")
                    DATABASE.SPOT1.status = "FREE"
                }
            }
            2 -> {
                if(DATABASE.SPOT2.status == "FREE") {
                    println("There is no car in spot 2.")
                }
                else {
                    println("Spot 2 is free.")
                    DATABASE.SPOT1.status = "FREE"
                }
            }
        }
    }

}

enum class DATABASE(var number: String = "NULL", var status: String = "FREE") {
    SPOT1(),
    SPOT2()
}