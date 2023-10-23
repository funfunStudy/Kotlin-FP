package funfun.study.kfp.chap6

fun <ITEM, RESULT> mapF(s: Rand<ITEM>, f: (ITEM) -> (RESULT)): Rand<RESULT> =
    flatMap(s) { item ->
        unit(f(item))
    }

fun <ITEM1, ITEM2, RESULT> map2F(ra: Rand<ITEM1>, rb: Rand<ITEM2>, f: (ITEM1, ITEM2) -> RESULT): Rand<RESULT> =
    flatMap(ra) { item1 ->
        mapF(rb) { item2 ->
            f(item1, item2)
        }
    }

