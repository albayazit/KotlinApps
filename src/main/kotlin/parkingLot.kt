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
        if(DATABASE.SPOT1.status == "free") {
            println("$color car parked in spot1")
            DATABASE.SPOT1.status = "busy"
        }
        else {
            println("$color car parked in spot2")
            DATABASE.SPOT2.status = "busy"
        }
    }

    fun leave() {}

}

enum class DATABASE(var status: String) {
    SPOT1("free"),
    SPOT2("free")
}