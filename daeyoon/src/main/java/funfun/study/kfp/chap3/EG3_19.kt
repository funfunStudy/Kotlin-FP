package funfun.study.kfp.chap3

fun <A, B> flatMap(xa: List<A>, f: (A) -> List<B>): List<B> =
    flatList(
        foldRight(xa, Nil as List<List<B>>) { h, t ->
            Cons(f(h), t)
        }
    )

fun <A, B> flatMap2(xs: List<A>, f: (A) -> List<B>): List<B> =
    foldRight(xs, Nil as List<B>) { h, t ->
        append(f(h), t)
    }

fun <A, B> flatMap3(xs: List<A>, f: (A) -> List<B>): List<B> =
    foldRight(xs, Nil as List<B>) { h, t ->
        foldRight(f(h), t) { h2, t2 ->
            Cons(h2, t2)
        }
    }


fun main() {
    val flatList = flatMap(List.of(1, 2, 3)) { i ->
        List.of(i, i)
    }
    val flatList2 = flatMap2(List.of(1, 2, 3)) { i ->
        List.of(i, i)
    }

    println(flatList)
    println(flatList2)
}
