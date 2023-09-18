package funfun.study.kfp.chap3

sealed class List<out ITEM> {
    companion object {
        fun <ITEM> of(vararg aa: ITEM): List<ITEM> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }
    }
}

object Nil : List<Nothing>()
data class Cons<out ITEM>(val head: ITEM, val tail: List<ITEM>): List<ITEM>()