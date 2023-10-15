package funfun.study.kfp.chap5

import funfun.study.kfp.chap4.None
import funfun.study.kfp.chap4.Some

fun <ITEM> Stream<ITEM>.tails(): Stream<Stream<ITEM>> =
    unfold(this) { item ->
        when(item) {
            is Cons -> Some(item to item.tail())
            is Empty -> None
        }
    }