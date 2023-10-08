package funfun.study.kfp.chap4

import funfun.study.kfp.chap3.*
import funfun.study.kfp.chap3.List

fun <ITEM, RESULT> traverse(list: List<ITEM>, transfer: (ITEM) -> Option<RESULT>): Option<List<RESULT>> =
    foldLeft(list, Some(Nil)) { acc: Option<List<RESULT>>, head: ITEM ->
        map2(acc, transfer(head)) { a, h ->
            Cons(h, a)
        }
    }

fun <ITEM, RESULT> traverse2(list: List<ITEM>, transfer: (ITEM) -> Option<RESULT>): Option<List<RESULT>> =
    sequenceL(map(list, transfer))

fun <ITEM, RESULT> traverse3(list: List<ITEM>, transfer: (ITEM) -> Option<RESULT>): Option<List<RESULT>> =
    when(list) {
        is Nil -> Some(Nil)
        is Cons -> map2(transfer(list.head), traverse3(list.tail, transfer)) { head, tail ->
            Cons(head, tail)
        }
    }
