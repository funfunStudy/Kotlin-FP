package funfun.study.kfp.chap3

fun plusAll(list: List<Int>, n: Int): List<Int> =
    foldRight(list, Nil as List<Int>) { h, t ->
        Cons(h + n, t)
    }


fun main() {
    val list1 = Cons(1, Cons(2, Cons(3, Nil)))
    val list2 = Cons(4, Cons(5, Cons(6, Nil)))

    println(plusAll(append(list1, list2), 1))
}