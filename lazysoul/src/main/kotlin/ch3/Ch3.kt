package ch3

sealed class List<out A> {
    companion object {
        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun List<Int>.sum(): Int =
            when (this) {
                is Nil -> 0
                is Cons -> head + tail.sum()
            }

        fun List<Double>.product(): Double =
            when (this) {
                is Nil -> 1.0
                is Cons -> head * tail.product()
            }

        fun <A> List<A>.tail(): List<A> =
            when (this) {
                is Nil -> Nil
                is Cons -> this.tail
            }

        fun <A, B> List<A>.foldRight(z: B, f: (A, B) -> B): B =
            when (this) {
                is Nil -> z
                is Cons -> f(head, tail.foldRight(z, f))
            }

        tailrec fun <A, B> List<A>.foldLeft(acc: B, f: (B, A) -> B): B =
            when (this) {
                is Nil -> acc
                is Cons -> tail.foldLeft(f(acc, this.head), f)
            }

        fun <A> List<A>.reverse(): List<A> =
            when (this) {
                is Nil -> Nil
                is Cons -> foldLeft(Nil as List<A>) { b, a -> Cons(a, b) }
            }

        fun <A, B> List<A>.map(f: (A) -> B): List<B> = foldRight(Nil as List<B>) { value, acc ->
            Cons(f(value), acc)
        }

        fun <A> List<A>.filter(f: (A) -> Boolean): List<A> = foldRight(Nil as List<A>) { value, acc ->
            if (f(value)) {
                Cons(value, acc)
            } else {
                acc
            }
        }
    }
}

data object Nil : List<Nothing>() // <2>

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()

object Ch3 {

}