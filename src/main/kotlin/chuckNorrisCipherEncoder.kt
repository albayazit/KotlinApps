fun main() {
    val cipher = CipherEncoder()
    while (true) {
        if(cipher.inputData() == 1) break
    }
}

class CipherEncoder {
    private var binary = ""
    private var word = ""
    private var binaryAll = ""
    fun inputData(): Int {
        println("Please input operation (encode/decode/exit):")
        when (val action = readln()) {
            "encode" -> encode()
            "decode" -> decode()
            "exit" -> {
                println("Bye!")
                return 1
            }
            else -> {
                println("There is no '${action}' operation")
            }
        }
        return 0
    }

    private fun encode() {
        println("Input string:")
        word = readln()
        convertToBinary()
        println("Encoded string:")
        code()
    }

    private fun convertToBinary() {
        binary = ""
        for(ch in word) {
            binary += String.format("%7S", Integer.toBinaryString(ch.toInt())).replace(' ', '0')
        }
    }

    private fun code() {
        var count = 0
        var result = ""
        while(count != binary.length) {
            if(binary[count] == '1') {
                result += "0 "
                for(i in count until binary.length) {
                    if(binary[i] == '1') {
                        result += "0"
                        count++
                    } else break
                }
            } else{
                result += "00 "
                for(i in count until binary.length) {
                    if(binary[i] == '0') {
                        result += "0"
                        count++
                    } else break
                }
            }
            result += ' '
        }
        println(result)
    }

    private fun decode() {
        println("Input encoded string:")
        word = readln()
        for(i in word) {
            if(i != '0' && i != ' '){
                println("Encoded string is not valid.")
                return
            }
        }
        if(codeToBinary() == 1) {
            println("Encoded string is not valid.")
            return
        }
        if(decodeToString() == 1) {
            println("Encoded string is not valid.")
            return
        }
    }

    private fun codeToBinary(): Int {
        var count = 0
        binaryAll = ""
        while(count < word.length) {
            if(count + 2 == word.length) return 1
            if (word[count] == '0' && word[count + 1] == '0' && word[count + 2] == ' ') {
                count += 3
                for (i in count until word.length) {
                    count++
                    if (word[i] == '0') {
                        binaryAll += '0'
                    } else break
                }
            } else if (word[count] == '0' && word[count + 1] == ' ') {
                count += 2
                for (i in count until word.length) {
                    count++
                    if (word[i] == '0') {
                        binaryAll += '1'
                    } else break
                }
            } else return 1
        }
        return 0
    }

    private fun decodeToString(): Int {
        var result = ""
        val chunkedBinary = binaryAll.chunked(7)
        for(i in chunkedBinary) {
            if(i.length != 7) return 1
        }
        println("Decoded string:")
        for(i in chunkedBinary.indices) {
            result += Integer.parseInt(chunkedBinary[i], 2).toChar()
        }
        println(result)
        return  0
    }
}