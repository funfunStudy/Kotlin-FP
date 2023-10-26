package funfun.study.kfp.chap3_practice

sealed class FList<out ITEM : Any> {
    companion object {
        operator fun <ITEM : Any> invoke(): FList<ITEM> = FNil
        operator fun <ITEM : Any> invoke(vararg aa: ITEM): FList<ITEM> {
            val tail = aa.sliceArray(1..<aa.size)
            return if (aa.isEmpty()) FNil else FCons(aa[0], invoke(*tail))
        }
    }
}

data object FNil : FList<Nothing>()
data class FCons<out ITEM : Any>(val head: ITEM, val tail: FList<ITEM>) : FList<ITEM>()