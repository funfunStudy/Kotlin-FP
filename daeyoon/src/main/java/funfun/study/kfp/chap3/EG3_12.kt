package funfun.study.kfp.chap3

fun <A, B> foldR(xs: List<A>, z: B, f: (A, B) -> B): B =
    foldLeft(xs, z) { a, b ->
        f(b, a)
    }

fun <A, B> foldL(xs: List<A>, z: B, f: (B, A) -> B): B =
    foldRight(xs, z) { a, b ->
        when (xs) {
            is Nil -> z
            is Cons -> {
                foldL(xs.tail, f(z, xs.head), f)
            }
        }
    }

fun main() {
    val list = Cons(1, Cons(2, Cons(3, Nil)))

    val result = foldL(list, 0) { a, b -> a + b }
    println(result)
}