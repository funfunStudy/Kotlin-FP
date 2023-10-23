package funfun.study.kfp.chap6

fun nonNegativeLessThan(n: Int): Rand<Int> = { rng ->
    val (i, rng2) = nonNegativeInt(rng)
    val mod = i % n
    if (i + (n - 1) - mod >= 0) {
        mod to rng
    } else {
        nonNegativeLessThan(n)(rng2)
    }
}

fun <ITEM, RESULT> flatMap(f: Rand<ITEM>, g: (ITEM) -> Rand<RESULT>): Rand<RESULT> = { rng ->
    val (item, rng1) = f(rng)
    g(item)(rng1)
}

fun nonNegativeLessThan2(n: Int): Rand<Int> =
    flatMap(::nonNegativeInt) { item: Int ->
        val mod = item % n

        if (item + (n - 1) - mod >= 0) {
            unit(mod)
        } else {
            nonNegativeLessThan2(n)
        }
    }