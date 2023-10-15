package funfun.study.kfp.chap5

fun <ITEM> Stream<ITEM>.startsWith(that: Stream<ITEM>): Boolean =
    this.zipWith(that) { item1, item2 ->
        item1 == item2
    }.forAll { it }

fun main() {
    val s = Stream.of(1,2,3,4,)
    val p = Stream.of(1,3)

    println(s.startsWith(p))
}