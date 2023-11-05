package funfun.study.kfp.chap7

fun <ITEM> parFilter(list: List<ITEM>, block: (ITEM) -> Boolean): Par<List<ITEM>> {
    val pars: List<Par<ITEM>> = list.map { lazyUnit { it } }
    return map(sequence(pars)) { la ->
        la.flatMap { a ->
            if (block(a)) listOf(a) else emptyList()
        }
    }
}