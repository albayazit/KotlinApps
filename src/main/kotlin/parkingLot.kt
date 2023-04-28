fun main() {
    val park = Parking()
    while(true) {
        if(park.input() == 1) break
    }
}

class Parking {
    private lateinit var data: List<String>
    private val db = DATABASE().database

    fun input(): Int {
        data = readln().split(' ')
        when(data[0]) {
            "create" -> create()
            "status" -> status()
            "park" -> park()
            "leave" -> leave()
            "exit" -> return 1
        }
        return 0
    }

    private fun create() {
        db.clear()
        val count = data[1].toInt()
        for(i in 1..count) {
            db.add(mutableListOf("$i", "NULL", "NULL", "FREE"))
        }
        println("Created a parking lot with $count spots.")
    }

    private fun status() {
        if(dataCheck() == 1) return
        var emptyCheck = true
        for(i in db.indices) {
            if(db[i][2] != "NULL") {
                println("${db[i][0]} ${db[i][1]} ${db[i][2]}")
                emptyCheck = false
            }
        }
        if(emptyCheck) println("Parking lot is empty.")
    }

    private fun park() {
        if(dataCheck() == 1) return
        val color = data[2]
        for(i in db.indices) {
            if(db[i][3] == "FREE") {
                println("$color car parked in spot ${i + 1}.")
                db[i][3] = "BUSY"
                db[i][1] = data[1]
                db[i][2] = data[2]
                return
            }
        }
        println("Sorry, the parking lot is full.")
    }

    private fun leave() {
        if(dataCheck() == 1) return
        val spot = data[1].toInt()
        for(i in db.indices) {
            if(i == spot - 1) {
                if(db[i][3] == "FREE") {
                    println("There is no car in spot ${spot}.")
                } else {
                    println("Spot $spot is free.")
                    db[i][3] = "FREE"
                    db[i][2] = "NULL"
                    db[i][1] = "NULL"
                }
                break
            }
        }
    }

    private fun dataCheck(): Int {
        if(db.isEmpty()) {
            println("Sorry, a parking lot has not been created.")
            return 1
        }
        return 0
    }
}

class DATABASE {
    var database = mutableListOf<MutableList<String>>()
}