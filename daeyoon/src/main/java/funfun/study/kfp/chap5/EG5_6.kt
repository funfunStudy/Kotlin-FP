package funfun.study.kfp.chap5

import funfun.study.kfp.chap4.None
import funfun.study.kfp.chap4.Option
import funfun.study.kfp.chap4.Some

fun <ITEM> Stream<ITEM>.headOption2(): Option<ITEM> =
    foldRight({ None }, { head, tail: () -> Option<ITEM> ->
        Some(head)
    })
