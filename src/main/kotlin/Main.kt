import learncoroutines.concept4.delay
import java.util.concurrent.Executors
import kotlin.coroutines.Continuation

/**
    try out the code snippets available in [learncoroutines] here

    example: [learncoroutines.concept1.makeBulkRequestsUsingCoroutines]


 */

private val executor = Executors.newSingleThreadScheduledExecutor{
    Thread(it, "scheduler").apply { isDaemon = true }
}
var continuation: Continuation<Unit>? = null

suspend fun main() {
    println("Before")
    delay(1000)
    println("After suspension")
}