package funfun.study.kfp.chap5

fun <ITEM> Stream<ITEM>.takeWhile(p: (ITEM) -> Boolean): Stream<ITEM> =
    when (this) {
        is Empty -> Empty
        is Cons -> if (p(head())) Cons(head, { tail().takeWhile(p) }) else Empty
    }