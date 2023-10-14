package funfun.study.kfp.chap5

fun from(n: Int): Stream<Int> = Cons.cons({ n }, { from(n + 1) })