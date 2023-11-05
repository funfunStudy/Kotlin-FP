package funfun.study.kfp.chap7

fun <A, B> asyncF(f: (A) -> B): (A) -> Par<B> =
    { a -> lazyUnit { f(a) } }