package learncoroutines.concept2

/**
    Limited forms of coroutines are used by other languages as well such as Python / JavaScript such as
    1.async functions (also called async/await)
    2.generator functions (yields subsequent values of a sequence).

    Kotlin Sequence is a sequence builder provided by kotlin instead of generator functions.

    Kotlin Sequence is a concept similar to collection but the elements are lazily evaluated
    (i.e) the next element is calculated on demand

    Control switches between [sequenceGenerator] and code snippets - [snippetOneAccessAllElementsInSequence] / [snippetTwoAccessOnlyTheFirstTwoElementsUsingIterator]
    as after every yield, the sequence builder will be suspended until the next element is requested.

    Upon executing the below snippets, It can be understood well. In the first
    snippet, you can see all the elements are yielded in output as for loop iterates through
    the entire sequence. In the second snippet, We request for only the first two elements of
    the sequence and thus the third element will not be printed in log
 */

private val sequenceGenerator = sequence {
    println("Generating first element")
    yield(1)
    println("Generating second element")
    yield(2)
    println("Generating third element")
    yield(3)
    println("Sequence Completed\n")
}

fun snippetOneAccessAllElementsInSequence() {
    println("Accessing All elements in Sequence")
    for (num in sequenceGenerator) {
        println("The next number is $num")
    }
}

fun snippetTwoAccessOnlyTheFirstTwoElementsUsingIterator() {
    val iterator = sequenceGenerator.iterator()
    println("Starting Sequence Using Iterator")
    println("The first element is ${iterator.next()}")
    println("The second element is ${iterator.next()}")
}