package funfun.study.kfp.chap3

fun <ITEM> drop(l: List<ITEM>, n: Int): List<ITEM> =
    if (n == 0) {
        l
    } else {
        drop(tail(l), n - 1)
    }