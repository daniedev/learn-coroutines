package learncoroutines.concept2

/**
    Even though there is no exit condition for the loop, the [printFibonacciSequence] will
    only print first 10 elements of the sequence as requested using [Sequence.take] operator
 */

internal val fibonacciSequence : Sequence<Int> = sequence {
    var firstNumber = 0
    var secondNumber = 1
    while (true) {
        yield(firstNumber)
        val temp = firstNumber
        firstNumber += secondNumber
        secondNumber = temp
    }
}

fun printFibonacciSequence() {
    fibonacciSequence.take(10).forEach { println(it) }

}