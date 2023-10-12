package funfun.study.kfp.chap5

fun <ITEM, RESULT> Stream<ITEM>.map(f: (ITEM) -> RESULT): Stream<RESULT> =
    foldRight({ Empty.empty() }, { head, tail: () -> Stream<RESULT> ->
        Cons({ f(head) }, tail)
    })

fun <ITEM> Stream<ITEM>.filter(f: (ITEM) -> Boolean): Stream<ITEM> =
    foldRight({ Empty.empty() }) { h, t ->
        if (f(h)) Cons({ h }, t) else t()
    }

fun <ITEM> Stream<ITEM>.append(sa: () -> Stream<ITEM>): Stream<ITEM> =
    foldRight(sa) { h, t ->
        Cons({ h }, t)
    }