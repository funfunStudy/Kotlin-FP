package funfun.study.kfp.chap3

fun <ITEM> dropWhile(l: List<ITEM>, f: (ITEM) -> Boolean): List<ITEM> =
    when(l) {
        is Nil -> Nil
        is Cons -> if(f(l.head)) dropWhile(l.tail, f) else l
    }