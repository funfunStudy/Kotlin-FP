package funfun.study.kfp.chap6

data class State<STATE, out ITEM>(val run: (STATE) -> Pair<ITEM, STATE>)

typealias Rand2<ITEM> = State<RNG, ITEM>