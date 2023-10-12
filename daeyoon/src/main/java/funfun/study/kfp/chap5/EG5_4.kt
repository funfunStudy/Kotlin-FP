package funfun.study.kfp.chap5

fun <ITEM> Stream<ITEM>.forAll(condition: (ITEM) -> Boolean): Boolean =
    when (this) {
        is Cons -> if (condition(head())) tail().forAll(condition) else false
        is Empty -> true
    }

fun <ITEM> Stream<ITEM>.forAll2(condition: (ITEM) -> Boolean): Boolean =
    foldRight({ true }, { head, tail -> condition(head) && tail() })

