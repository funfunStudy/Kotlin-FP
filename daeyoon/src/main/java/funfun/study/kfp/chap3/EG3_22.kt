package funfun.study.kfp.chap3

fun <A> zipWith(list1: List<A>, list2: List<A>, combine: (A, A) -> A): List<A> =
    when (list1) {
        is Nil -> Nil
        is Cons -> {
            when (list2) {
                is Nil -> Nil
                is Cons -> Cons(combine(list1.head, list2.head), zipWith(list1.tail, list2.tail, combine))
            }
        }
    }