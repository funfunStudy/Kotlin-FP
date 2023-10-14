package funfun.study.kfp.chap5

fun <ITEM> constant(item: ITEM): Stream<ITEM> = Cons.cons({ item }, { constant(item) })