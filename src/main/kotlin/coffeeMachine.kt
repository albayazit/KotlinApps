fun main() {
    val action = Action()
    val espresso = Coffee()
    val latte = Coffee()
    val cappuccino = Coffee()

    coffeeSate(espresso, latte, cappuccino)
    currentState(action)
    selectAction(action, espresso, latte, cappuccino)
    currentState(action)
}

class Coffee() {
    var waterNeed = 0
    var milkNeed = 0
    var beansNeed = 0
    var cost = 0
}

class Action() {
    var water = 400
    var milk = 540
    var beans = 120
    var cups = 9
    var money = 550
}

fun currentState(action: Action) {
    println("The coffee machine has:")
    println("${action.water} ml of water")
    println("${action.milk} ml of milk")
    println("${action.beans} g of coffee beans")
    println("${action.cups} disposable cups")
    println("$${action.money} of money\n")
}

fun coffeeSate(espresso: Coffee, latte: Coffee, cappuccino: Coffee) {
    espresso.waterNeed = 250
    espresso.beansNeed = 16
    espresso.cost = 4

    latte.waterNeed = 350
    latte.milkNeed = 75
    latte.beansNeed = 20
    latte.cost = 7

    cappuccino.waterNeed = 200
    cappuccino.milkNeed = 100
    cappuccino.beansNeed = 12
    cappuccino.cost = 6
}


fun selectAction(action: Action, espresso: Coffee, latte: Coffee, cappuccino: Coffee) {
    println("Write action (buy, fill, take):")
    val select = readln()
    when (select) {
        "buy" -> buy(action, espresso, latte, cappuccino)
        "fill" -> fill(action)
        "take" -> take(action)
    }

}

fun buy(action: Action, espresso: Coffee, latte: Coffee, cappuccino: Coffee) {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")
    val coffee = readln().toInt()
    when (coffee) {
        1 -> {
            if(checkReserves(action, espresso)) buyCoffee(action, espresso)
        }
        2 -> {
            if(checkReserves(action, latte)) buyCoffee(action, latte)
        }
        3 -> {
            if(checkReserves(action, cappuccino)) buyCoffee(action, cappuccino)
        }
    }
}

fun checkReserves(action: Action, coffee: Coffee): Boolean {
    return action.water >= coffee.waterNeed &&
            action.milk >= coffee.milkNeed &&
            action.beans >= coffee.beansNeed &&
            action.cups >= 1
}

fun buyCoffee(action: Action, coffee: Coffee) {
    action.water -= coffee.waterNeed
    action.milk -= coffee.milkNeed
    action.beans -= coffee.beansNeed
    action.money += coffee.cost
    action.cups -= 1
}

fun fill(action: Action) {
    println("Write how many ml of water you want to add:")
    action.water += readln().toInt()
    println("Write how many ml of milk you want to add:")
    action.milk += readln().toInt()
    println("Write how many grams of coffee beans you want to add: ")
    action.beans += readln().toInt()
    println("Write how many disposable cups you want to add: ")
    action.cups += readln().toInt()
}

fun take(action: Action) {
    println("I gave you ${action.money}")
    action.money = 0
}