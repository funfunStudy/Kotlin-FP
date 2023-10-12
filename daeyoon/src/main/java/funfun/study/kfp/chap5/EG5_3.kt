package funfun.study.kfp.chap5

fun <ITEM> Stream<ITEM>.takeWhile(p: (ITEM) -> Boolean): Stream<ITEM> =
    when (this) {
        is Empty -> Empty
        is Cons -> if (p(head())) Cons(head, { tail().takeWhile(p) }) else Empty
    }

fun <ITEM> Stream<ITEM>.exists(p: (ITEM) -> Boolean): Boolean =
    when (this) {
        is Cons -> p(head()) || this.tail().exists(p)
        else -> false
    }

fun <ITEM, RESULT> Stream<ITEM>.foldRight(
    default: () -> RESULT,
    combine: (ITEM, () -> RESULT) -> RESULT
): RESULT =
    when (this) {
        is Cons -> combine(head(), { tail().foldRight(default, combine) })
        is Empty -> default()
    }

fun <ITEM> Stream<ITEM>.exists2(p: (ITEM) -> Boolean): Boolean =
    foldRight({ false }, { a, b -> p(a) || b() })