fun main() {
    val encoder = cipherEncoder()
    encoder.inputData()
    encoder.convertToBinary()
}

class cipherEncoder {
    var binary = ""
    var word = ""
    var encoded = ""
    fun inputData() {
        println("Input string:")
        word = readln()
    }

    fun convertToBinary() {
        for(ch in word) {
            binary = String.format("%7S", Integer.toBinaryString(ch.toInt())).replace(' ', '0')
            encodeBinary(binary)
        }
    }

    fun encodeBinary(binary: String) {
    }
}