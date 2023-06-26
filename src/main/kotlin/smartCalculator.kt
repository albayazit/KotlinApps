fun main() {
    val calculator = Calculator()
    calculator.start()
}

class Calculator {
    val operation = Operation()
    fun start() {
        val numbers = input().split(" ")
        val a = numbers[0].toInt()
        val b = numbers[1].toInt()
        val result = operation.add(a, b)
        println(result)
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