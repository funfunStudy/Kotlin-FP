package funfun.study.kfp.chap5

import funfun.study.kfp.chap4.None
import funfun.study.kfp.chap4.Option
import funfun.study.kfp.chap4.Some

fun <ITEM, SEED> unfold(seed: SEED, generate: (SEED) -> Option<Pair<ITEM, SEED>>): Stream<ITEM> =
    when (val gen = generate(seed)) {
        is None -> Empty
        is Some -> {
            val (curItem : ITEM, nextSeed : SEED) = gen.get
            Cons.cons({ curItem }, { unfold(nextSeed, generate) })
        }
    }
