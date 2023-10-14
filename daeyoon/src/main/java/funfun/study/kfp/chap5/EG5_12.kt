package funfun.study.kfp.chap5

import funfun.study.kfp.chap4.Some

fun ones2(): Stream<Int> = unfold(1) {
    Some(1 to 1)
}

fun <A> constant2(a: A): Stream<A> =
    unfold(a) {
        Some(a to a)
    }

fun from2(n: Int): Stream<Int> =
    unfold(n) {
        Some(it to it + 1)
    }

fun fibs2(): Stream<Int> =
    unfold(0 to 1) { (cur, next) ->
        Some(cur to (next to (cur + next)))
    }