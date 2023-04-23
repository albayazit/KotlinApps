fun main() {
    val cipher = cipherEncoder()
    cipher.inputData()
    cipher.convertToBinary()
    cipher.decode()
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
            binary += String.format("%7S", Integer.toBinaryString(ch.toInt())).replace(' ', '0')
        }
    }

    fun code() {
        var count = 0
        while(count != binary.length) {
            if(binary[count] == '1') {
                print("0 ")
                for(i in count until binary.length) {
                    if(binary[i] == '1') {
                        print('0')
                        count++
                    } else break
                }
                print(' ')
            } else{
                print("00 ")
                for(i in count until binary.length) {
                    if(binary[i] == '0') {
                        print('0')
                        count++
                    } else break
                }
                print(' ')
            }
        }
    }

    fun decode() {
        var count = 0
        while(count != binary.length) {

        }
    }
}