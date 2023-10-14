package funfun.study.kfp.chap5

import funfun.study.kfp.chap4.None
import funfun.study.kfp.chap4.Option
import funfun.study.kfp.chap4.Some

fun <A, S> unfold(z: S, f: (S) -> Option<Pair<A, S>>): Stream<A> =
    when (val s = f(z)) {
        is None -> Empty
        is Some -> {
            val (cur, next) = s.get
            Cons.cons({ cur }, { unfold(next, f) })
        }
    }