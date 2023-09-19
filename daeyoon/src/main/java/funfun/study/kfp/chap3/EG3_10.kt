package funfun.study.kfp.chap3

fun sumL(ints: List<Int>): Int =
    foldLeft(ints, 0) { a, b ->
        a + b
    }

fun productL(dbs: List<Double>): Double =
    foldLeft(dbs, 0.0) { a, b ->
        a * b
    }

fun <A> lengthL(xs: List<A>): Int =
    foldLeft(xs, 0) { a, _ ->
        a + 1
    }