package funfun.study.kfp.chap7

import funfun.study.kfp.chap4.map2
import java.util.concurrent.TimeUnit

object Pars {
    fun <ITEM> unit(item: ITEM): Par<ITEM> = { es -> UnitFuture(item) }

    data class UnitFuture<ITEM>(val item: ITEM) : Future<ITEM> {
        override fun get(): ITEM = item

        override fun get(timeout: Long, timeUnit: TimeUnit): ITEM = item

        override fun cancel(evenIfRunning: Boolean): Boolean = false

        override fun isDone(): Boolean = true

        override fun isCancelled(): Boolean = false
    }

    fun <ITEM1, ITEM2, RESULT> map2(
        item1: Par<ITEM1>,
        item2: Par<ITEM2>,
        block: (ITEM1, ITEM2) -> RESULT
    ): Par<RESULT> = { es ->
        val future1 = item1(es)
        val future2 = item2(es)
        UnitFuture(block(future1.get(), future2.get()))
    }


    fun <ITEM1, ITEM2, RESULT> map2_timeout(
        item1: Par<ITEM1>,
        item2: Par<ITEM2>,
        block: (ITEM1, ITEM2) -> RESULT
    ): Par<RESULT> = { es ->
        val future1 = item1(es)
        val future2 = item2(es)
        TimedMap2Future(future1, future2, block)
    }

    fun <ITEM> fork(
        item: () -> Par<ITEM>
    ): Par<ITEM> = { es: ExecutorService ->
        es.submit(object : Callable<ITEM> {
            override fun call(): ITEM = item()(es).get()
        })
    }
}

fun main() {
    val fork = Pars.fork {
        Pars.map2(Pars.unit(1), Pars.unit(2)) { item1, item2 ->
            item1 + item2
        }
    }
}