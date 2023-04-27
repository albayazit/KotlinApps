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
                return
            }
        }
        println("Sorry, the parking lot is full.")
    }

    fun leave() {
        val spot = data[1].toInt()
        for(i in DATABASE.values()) {
            if(i.ordinal == spot - 1) {
                if(i.status == "FREE") {
                    println("There is no car in spot ${spot}.")
                }
                else {
                    println("Spot ${spot} is free.")
                    i.status = "FREE"
                }
                break
            }
        }
    }

}

enum class DATABASE(var number: String = "NULL", var status: String = "FREE") {
    SPOT1(), SPOT2(), SPOT3(), SPOT4(), SPOT5(), SPOT6(), SPOT7(), SPOT8(), SPOT9(), SPOT10(),
    SPOT11(), SPOT12(), SPOT13(), SPOT14(), SPOT15(), SPOT16(), SPOT17(), SPOT18(), SPOT19(), SPOT20()
}