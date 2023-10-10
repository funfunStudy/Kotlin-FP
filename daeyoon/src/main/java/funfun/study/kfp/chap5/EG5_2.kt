package funfun.study.kfp.chap5

fun <ITEM> Stream<ITEM>.take(n: Int): Stream<ITEM> =
    if (n == 0) Empty
    else when (this) {
        is Empty -> Empty
        is Cons -> Cons(head, { tail().take(n - 1) })
    }

fun <ITEM> Stream<ITEM>.drop(n: Int): Stream<ITEM> =
    if (n == 0) this
    else when (this) {
        is Empty -> Empty
        is Cons -> tail().drop(n - 1)
    }