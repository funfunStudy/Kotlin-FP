package funfun.study.kfp.chap3

fun mapDoubleToString(list: List<Double>): List<String> =
    foldRight(list, Nil as List<String>) { h, t ->
        Cons(h.toString(), t)
    }