package funfun.study.kfp.chap7
//
//class Par<ITEM>(val get: ITEM) {
//    companion object {
//        fun <ITEM> unit(item: ITEM): Par<ITEM> = Par(item)
//
//        fun <ITEM1, ITEM2, RESULT> map2(
//            item1: Par<ITEM1>,
//            item2: Par<ITEM2>,
//            block: (ITEM1, ITEM2) -> RESULT
//        ): Par<RESULT> = Par(block(item1.get, item2.get))
//
//        fun <ITEM> fork(block: () -> Par<ITEM>): Par<ITEM> = block()
//
//        fun <ITEM> lazyUnit(item: () -> ITEM): Par<ITEM> = Par(item())
//
//        fun <ITEM> run(item: Par<ITEM>): ITEM = item.get
//    }
//}