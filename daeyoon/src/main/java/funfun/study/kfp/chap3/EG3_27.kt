package funfun.study.kfp.chap3

fun <A, B> map(root: Tree<A>, f: (A) -> B): Tree<B> =
    when(root) {
        is Leaf -> Leaf(f(root.value))
        is Branch -> Branch(map(root.left, f), map(root.right, f))
    }

