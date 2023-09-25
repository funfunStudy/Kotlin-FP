package funfun.study.kfp.chap3

fun <A> size(root: Tree<A>): Int =
    when(root) {
        is Leaf -> 1
        is Branch -> size(root.left) + size(root.right) + 1
    }


fun main() {
    val root = Branch(Leaf(0), Branch(Leaf(1), Leaf(2)))
    val root2 = Leaf(0)
    println(size(root))
}