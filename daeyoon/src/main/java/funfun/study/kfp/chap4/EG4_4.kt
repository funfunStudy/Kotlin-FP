package funfun.study.kfp.chap4

import funfun.study.kfp.chap3.*
import funfun.study.kfp.chap3.List


fun <ITEM> sequence(list: List<Option<ITEM>>): Option<List<ITEM>> =
    foldRight(list, Some(Nil)) { head: Option<ITEM>, result: Option<List<ITEM>> ->
        map2(head, result) { h: ITEM, r: List<ITEM> ->
            Cons(h, r)
        }
    }

fun <ITEM> sequenceL(list: List<Option<ITEM>>): Option<List<ITEM>> =
    foldLeft(list, Some(Nil)) { result: Option<List<ITEM>>, head: Option<ITEM> ->
        map2(result, head) { r, h ->
            Cons(h, r)
        }
    }