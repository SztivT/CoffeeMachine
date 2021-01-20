package machine

import java.util.*
import kotlin.system.exitProcess

class CoffeeMachine {
    private var water = 400
    private var milk = 540
    private var beans = 120
    private var cups = 9
    private var cash = 550

    fun getInfo() {
        println("\nThe coffee machine has:")
        println("$water of water")
        println("$milk of milk")
        println("$beans of coffee beans")
        println("$cups of disposable cups")
        println("$cash of money\n")
    }

    fun cashOut() {
        println("I gave you $$cash")
        cash = 0
    }

    fun fill() {
        println("Write how many ml of water do you want to add:")
        water += scanner.nextInt()
        println("Write how many ml of milk do you want to add:")
        milk += scanner.nextInt()
        println("Write how many grams of coffee beans do you want to add:")
        beans += scanner.nextInt()
        println("Write how many disposable cups of coffee do you want to add:")
        cups += scanner.nextInt()
    }

    fun buy() {
        print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        val b = when (scanner.next()) {
            "1" -> Espresso()
            "2" -> Latte()
            "3" -> Cappuccino()
            "back" -> return
            else -> return
        }
        when {
            water < b.water -> println("Sorry, not enough water!\n")
            milk < b.milk -> println("Sorry, not enough milk!\n")
            beans < b.beans -> println("Sorry, not enough coffee beans!\n")
            cups < 1 -> println("Sorry, not enough disposable cups!\n")
            else -> {
                println("I have enough resources, making you a coffee!\n")
                this.water -= b.water
                this.milk -= b.milk
                this.beans -= b.beans
                this.cups -= b.cup
                this.cash += b.price
            }
        }
    }
}

open class Coffee(val water: Int = 0, val milk: Int = 0, val beans: Int = 0, val price: Int = 0) {
    val cup = 1

}

class Espresso : Coffee(water = 250, beans = 16, price = 4)
class Latte : Coffee(water = 350, milk = 75, beans = 20, price = 7)
class Cappuccino : Coffee(water = 200, milk = 100, beans = 12, price = 6)

val scanner = Scanner(System.`in`)
fun main() {
    val c = CoffeeMachine()
    menu(c)
}
fun menu (c: CoffeeMachine) {
    print("Write action (buy, fill, take, remaining, exit):")
    when (scanner.next()) {
        "buy" -> c.buy()
        "fill" -> c.fill()
        "take" -> c.cashOut()
        "remaining" -> c.getInfo()
        "exit" -> exitProcess(0)
    }
    menu(c)
}