package funfun.study.kfp.chap5

fun <ITEM> Stream<ITEM>.takeWhile2(p: (ITEM) -> Boolean): Stream<ITEM> =
    foldRight(
        { Empty },
        { head, tail: () -> Stream<ITEM> ->
            if (p(head)) Cons({ head }, { tail().takeWhile2(p) }) else Empty
        }
    )
