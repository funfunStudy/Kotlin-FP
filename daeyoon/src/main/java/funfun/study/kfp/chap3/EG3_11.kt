package funfun.study.kfp.chap3

fun <A> reverse(xs: List<A>): List<A> {
    fun go(origin: List<A>, tail: List<A>): List<A> =
        when (origin) {
            is Nil -> tail
            is Cons -> go(origin.tail, Cons(origin.head, tail))
        }

    return go(xs, Nil)
}

fun <A> reverse2(xs: List<A>): List<A> =
    foldLeft(xs, Nil as List<A>) { a, b ->
        Cons(b, a)
    }

fun main() {
    val list = Cons(1, Cons(2, Cons(3, Nil)))
    val reverse = reverse(list)
    val reverse2 = reverse2(list)

    println(reverse)
    println(reverse2)
}