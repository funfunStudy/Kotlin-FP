package funfun.study.kfp.chap6

import kotlin.random.Random

fun main() {
    val rng = Random.nextInt();
}

fun rollDice(): Int {
    val rng = Random
    return rng.nextInt(6)
}