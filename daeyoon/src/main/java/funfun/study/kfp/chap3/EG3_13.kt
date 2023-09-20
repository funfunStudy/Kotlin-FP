package funfun.study.kfp.chap3

fun <A> append(a1: List<A>, a2: List<A>): List<A> =
    foldRight(a1, a2) { a, b ->
        Cons(a, b)
    }

fun main() {
    val list1 = Cons(1, Cons(2, Cons(3, Nil)))
    val list2 = Cons(4, Cons(5, Cons(6, Nil)))

    println(append(list1, list2))
}
