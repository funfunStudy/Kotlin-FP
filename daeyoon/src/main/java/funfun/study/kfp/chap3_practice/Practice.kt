package funfun.study.kfp.chap3_practice

fun <ITEM : Any> tail(xs: FList<ITEM>): FList<ITEM> =
    when (xs) {
        is FNil -> FNil
        is FCons -> xs.tail
    }

fun <ITEM : Any> setHead(xs: FList<ITEM>, x: ITEM): FList<ITEM> =
    when (xs) {
        is FNil -> FCons(x, FNil)
        is FCons -> FCons(x, xs.tail)
    }

fun <ITEM : Any> drop(l: FList<ITEM>, n: Int): FList<ITEM> =
    when (l) {
        is FNil -> FNil
        is FCons -> if (n > 0) drop(l.tail, n - 1) else l
    }

fun <ITEM : Any> dropWhile(l: FList<ITEM>, f: (ITEM) -> Boolean): FList<ITEM> =
    when (l) {
        is FNil -> FNil
        is FCons -> if (f(l.head)) dropWhile(l.tail, f) else l
    }

fun <ITEM : Any> dropWhileIndexed(idx: Int = 0, l: FList<ITEM>, f: (Int, ITEM) -> Boolean): FList<ITEM> =
    when (l) {
        is FNil -> FNil
        is FCons -> if (f(idx, l.head)) dropWhileIndexed(idx + 1, l.tail, f) else l
    }
