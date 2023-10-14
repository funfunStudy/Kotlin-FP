package funfun.study.kfp.chap5

import funfun.study.kfp.chap4.None
import funfun.study.kfp.chap4.Some

fun <ITEM, RESULT> Stream<ITEM>.map2(f: (ITEM) -> RESULT): Stream<RESULT> =
    unfold(this) {
        when (it) {
            is Empty -> None
            is Cons -> {
                Some(f(it.head()) to it.tail())
            }
        }
    }