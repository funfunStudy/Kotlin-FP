package funfun.study.kfp.chap3

tailrec fun <A, B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B =
    when(xs) {
        is Nil -> z
        is Cons -> foldLeft(xs, f(z, xs.head), f)
    }