package funfun.study.kfp.chap3

fun <ITEM> init(l: List<ITEM>): List<ITEM> =
    when(l) {
        is Nil -> Nil
        is Cons -> {
            when (l.tail) {
                is Nil -> Nil
                is Cons -> Cons(l.head, init(l.tail))
            }
        }
    }
