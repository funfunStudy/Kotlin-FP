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
        fun <ITEM> cons(hd: () -> ITEM, tl: () -> Stream<ITEM>): Stream<ITEM> {
            val head: ITEM by lazy(hd)
            val tail: Stream<ITEM> by lazy(tl)
            return Cons({ head }, { tail })
        }

        fun <ITEM> cons2(hd: () -> ITEM, tl: () -> Stream<ITEM>): Stream<ITEM> {
            val head = hd()
            val tail = tl()
            return Cons({ head }, { tail })
        }
        fun <ITEM> cons3(hd: () -> ITEM, tl: () -> Stream<ITEM>): Stream<ITEM> {
            return Cons({ hd() }, { tl() })
        }
    }
}

object Empty : Stream<Nothing>() {
    fun <ITEM> empty(): Stream<ITEM> = Empty
}
