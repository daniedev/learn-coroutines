package learncoroutines.concept1

import kotlinx.coroutines.*
import kotlin.concurrent.thread

/**
This scenario is a simulation of 100000 users requesting for data to a backend service.

[makeBulkRequestsUsingThread] starts 100000 threads, makes them sleep for 1 second
and then print the dot on screen (simulation of waiting for data to be retrieved). If you observe, It will take a while for the execution to complete.

Here in [makeBulkRequestsUsingCoroutines], It starts 100000 coroutines and suspends all of them instead of making them sleep, waits for a second
and prints all the dots. Cost of starting these coroutines is so cheap that its barely noticeable.
 */

fun makeBulkRequestsUsingThread() {
    repeat(100000) {
        thread {
            Thread.sleep(1000L)
            print(".")
        }
    }
}

fun makeBulkRequestsUsingCoroutines() = runBlocking {
    repeat(100000) {
        launch {
            delay(1000L)
            print(".")
        }
    }
}