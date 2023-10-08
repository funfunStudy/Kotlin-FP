package funfun.study.kfp.chap4

import funfun.study.kfp.chap3.Cons
import funfun.study.kfp.chap3.List
import funfun.study.kfp.chap3.Nil
import funfun.study.kfp.chap3.foldLeft

fun <ERROR, ITEM> sequence(list: List<Either<ERROR, ITEM>>): Either<ERROR, List<ITEM>> =
    foldLeft(list, Right(Nil)) { acc: Either<ERROR, List<ITEM>>, head: Either<ERROR, ITEM> ->
        map2(acc, head) { a, h ->
            Cons(h, a)
        }
    }

fun <ERROR, ITEM, RESULT> traverse(
    list: List<ITEM>,
    transfer: (ITEM) -> Either<ERROR, RESULT>
): Either<ERROR, List<RESULT>> =
    foldLeft(list, Right(Nil)) { acc: Either<ERROR, List<RESULT>>, head: ITEM ->
        map2(acc, transfer(head)) { a, h ->
            Cons(h, a)
        }
    }

fun <ERROR, ITEM, RESULT> traverse2(
    list: List<ITEM>,
    transfer: (ITEM) -> Either<ERROR, RESULT>
): Either<ERROR, List<RESULT>> =
    when (list) {
        is Nil -> Right(Nil)
        is Cons -> {
            map2(transfer(list.head), traverse2(list.tail, transfer)) { head, tail ->
                Cons(head, tail)
            }
        }
    }