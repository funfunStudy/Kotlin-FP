package funfun.study.kfp.chap7

import java.util.concurrent.TimeUnit

interface Callable<ITEM> {
    fun call(): ITEM
}

interface Future<ITEM> {
    fun get(): ITEM
    fun get(timeout: Long, timeUnit: TimeUnit): ITEM
    fun cancel(evenIfRunning: Boolean): Boolean
    fun isDone(): Boolean
    fun isCancelled(): Boolean
}

interface ExecutorService {
    fun <ITEM> submit(c: Callable<ITEM>): Future<ITEM>
}

typealias Par<ITEM> = (ExecutorService) -> Future<ITEM>
fun <ITEM> run(es: ExecutorService, item: Par<ITEM>): Future<ITEM> = item(es)