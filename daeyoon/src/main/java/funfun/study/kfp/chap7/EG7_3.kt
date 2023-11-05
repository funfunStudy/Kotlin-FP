package funfun.study.kfp.chap7

import java.util.concurrent.TimeUnit

data class TimedMap2Future<ITEM1, ITEM2, RESULT>(
    val item1: Future<ITEM1>,
    val item2: Future<ITEM2>,
    val block: (ITEM1, ITEM2) -> RESULT
) : Future<RESULT> {
    override fun get(): RESULT {
        TODO("Not yet implemented")
    }

    override fun get(timeout: Long, timeUnit: TimeUnit): RESULT {
        val timeoutMillis = TimeUnit.MICROSECONDS.convert(timeout, timeUnit)

        val start = System.currentTimeMillis()
        val a = item1.get(timeout, timeUnit)
        val duration = System.currentTimeMillis() - start

        val remainder = timeoutMillis - duration
        val b = item2.get(remainder, TimeUnit.MICROSECONDS)
        return block(a, b)
    }

    override fun cancel(evenIfRunning: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun isDone(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCancelled(): Boolean {
        TODO("Not yet implemented")
    }


}