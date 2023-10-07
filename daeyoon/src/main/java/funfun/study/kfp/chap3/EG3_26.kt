package funfun.study.kfp.chap3

fun <A> depth(root: Tree<A>, n: Int): Int =
    when (root) {
        is Leaf -> n
        is Branch -> maxOf(depth(root.left, n + 1), depth(root.right, n + 1))
    }

fun main() {
    val root = Branch(Leaf(0), Branch(Leaf(1), Leaf(2)))

    println(depth(root, 0))
}