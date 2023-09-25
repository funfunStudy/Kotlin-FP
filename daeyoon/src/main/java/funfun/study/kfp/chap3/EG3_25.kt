package funfun.study.kfp.chap3


fun maximum(root: Tree<Int>): Int =
    when(root) {
        is Leaf -> root.value
        is Branch -> maxOf(maximum(root.left), maximum(root.right))
    }

fun main() {
    val root = Branch(Leaf(0), Branch(Leaf(1), Leaf(2)))

    println(maximum(root))
}