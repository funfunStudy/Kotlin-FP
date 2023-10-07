package funfun.study.kfp.chap3

sealed class Tree<out A>
data class Leaf<A>(val value: A) : Tree<A>()
data class Branch<A>(val left: Tree<A>, val right: Tree<A>) : Tree<A>()

fun <A, B> fold(ta: Tree<A>, l: (A) -> B, b: (B, B) -> B): B =
    when (ta) {
        is Leaf -> l(ta.value)
        is Branch -> b(fold(ta.left, l, b), fold(ta.right, l, b))
    }

fun <A> sizeF(ta: Tree<A>): Int =
    fold(
        ta = ta,
        l = { a ->
            1
        },
        b = { b1, b2 ->
            b1 + b2 + 1
        }
    )

fun maximumF(ta: Tree<Int>): Int =
    fold(
        ta = ta,
        l = {
            it
        },
        b = { b1, b2 ->
            maxOf(b1, b2)
        }
    )

fun <A> depthF(ta: Tree<A>): Int =
    fold(
        ta = ta,
        l = {
            0
        },
        b = { b1, b2 ->
            1 + maxOf(b1, b2)
        }
    )

fun <A, B> mapF(ta: Tree<A>, f: (A) -> B): Tree<B> =
    fold(ta, { Leaf(f(it)) }) { b1: Tree<B>, b2: Tree<B> ->
        Branch(b1, b2)
    }