package funfun.study.kfp.chap3

fun <ITEM> setHead(xs: List<ITEM>, x: ITEM): List<ITEM> =
    when(xs) {
        is Nil -> Nil
        is Cons -> Cons(x, xs.tail)
    }
