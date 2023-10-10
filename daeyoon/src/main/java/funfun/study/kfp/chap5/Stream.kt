package funfun.study.kfp.chap5

sealed class Stream<out ITEM> {
    companion object {
        fun <ITEM> of(vararg xs: ITEM): Stream<ITEM> =
            if (xs.isEmpty()) Empty.empty()
            else Cons.cons({ xs[0] }, { of(*xs.sliceArray(1..<xs.size)) })
    }
}

data class Cons<out ITEM>(
    val head: () -> ITEM,
    val tail: () -> Stream<ITEM>
) : Stream<ITEM>() {
    companion object {
        fun <ITEM> cons(head: () -> ITEM, tail: () -> Stream<ITEM>): Stream<ITEM> {
            val head: ITEM by lazy(head)
            val tail: Stream<ITEM> by lazy(tail)
            return Cons({ head }, { tail })
        }
    }
}

object Empty : Stream<Nothing>() {
    fun <ITEM> empty(): Stream<ITEM> = Empty
}
