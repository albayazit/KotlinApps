fun main() {
    val cipher = cipherEncoder()
    cipher.inputData()
    cipher.convertToBinary()
    cipher.decode()
    cipher.decodeToString()
}

class cipherEncoder {
    var binary = ""
    var word = ""
    var binaryAll = ""
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
        while(count < word.length) {
            if (word[count] == '0' && word[count + 1] == '0') {
                count += 3
                for (i in count until word.length) {
                    count++
                    if (word[i] == '0') {
                        binaryAll += '0'
                    } else break
                }
            } else {
                count += 2
                for (i in count until word.length) {
                    count++
                    if (word[i] == '0') {
                        binaryAll += '1'
                    } else break
                }
            }
        }
    }

//    fun decodeToString() {
//        var count = 0
//        var stringNum = ""
//        for(i in binaryAll.indices) {
//            if(count % 7 == 0) {
//                println(stringNum)
//            }
//            count++
//        }
//    }
}