package funfun.study.kfp.chap5

fun <ITEM, RESULT> Stream<ITEM>.map(f: (ITEM) -> RESULT): Stream<RESULT> =
    foldRight({ Empty.empty() }, { head, tail: () -> Stream<RESULT> ->
        Cons({ f(head) }, tail)
    })

fun <ITEM> Stream<ITEM>.filter(f: (ITEM) -> Boolean): Stream<ITEM> =
    foldRight({ Empty.empty() }) { h, t ->
        if (f(h)) Cons({ h }, t) else t()
    }

fun <ITEM> Stream<ITEM>.append(sa: () -> Stream<ITEM>): Stream<ITEM> =
    foldRight(sa) { h, t ->
        Cons({ h }, t)
    }

fun Stream<Int>.sum(): Int =
    foldRight({ 0 }) { h, t ->
        h + t()
    }

fun ones(): Stream<Int> = Cons.cons({ 1 }, { ones() })

fun main() {
    val s = Stream.of(1,2,3,4)
    println(s.sum())

    println(ones().take(5).toList())
    println(ones().exists { it % 2 != 0 })
    println(ones().map { it + 1 }.exists { it % 2 == 0 })
    println(ones().takeWhile { it == 1 })
//    println(ones().forAll2 { it == 1 })
//    println(ones().takeWhile { it == 1 }.toList())
//    println(ones().exists { it % 2 == 0 })


}