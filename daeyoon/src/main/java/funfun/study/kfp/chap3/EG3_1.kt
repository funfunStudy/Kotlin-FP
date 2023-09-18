package funfun.study.kfp.chap3

fun <ITEM> tail(xs: List<ITEM>): List<ITEM> =
    when (xs) {
        is Nil -> Nil
        is Cons -> xs.tail
    }
