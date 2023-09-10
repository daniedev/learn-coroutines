package learncoroutines.concept4

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor {
    Thread(it, "scheduler").apply { isDaemon = true }
}

suspend fun delay(timeMillis: Long) {
    suspendCoroutine {
        executor.schedule({ it.resume(Unit) }, timeMillis, TimeUnit.MILLISECONDS)
    }
}