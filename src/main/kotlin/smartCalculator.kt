fun main() {
    val calculator = Calculator()
    calculator.start()
}

class Calculator {
    val operation = Operation()
    fun start() {
        while (true) {
            val numbers = input().split(" ")
            if (numbers[0] == "/exit") {
                println("Bye!")
                break
            }
            else if (numbers[0] == "") continue
            else if (numbers.size == 1) println(numbers[0])
            else {
                val a = numbers[0].toIntOrNull() ?: 0
                val b = numbers[1].toIntOrNull() ?: 0
                val result = operation.add(a, b)
                println(result)
            }
        }
    }

    fun input(): String {
        return readln()
    }


    inner class Operation {
        fun add(a: Int, b: Int): Int {
            return a + b
        }
    }
}