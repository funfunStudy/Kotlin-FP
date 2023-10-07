package funfun.study.kfp.chap3

fun <A, B> map(xs: List<A>, f: (A) -> B): List<B> =
    foldRight(xs, Nil as List<B>) { h, t ->
        Cons(f(h), t)
    }