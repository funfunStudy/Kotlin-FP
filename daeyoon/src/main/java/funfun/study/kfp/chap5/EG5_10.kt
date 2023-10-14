package funfun.study.kfp.chap5

fun fibs(): Stream<Int> {
    fun go(a: Int, b: Int): Stream<Int> {
        return Cons.cons({ a }, { go(b, a + b) })
    }
    return go(0, 1)
}