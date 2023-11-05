package funfun.study.kfp.chap7

import funfun.study.kfp.chap7.Pars.unit

fun <K, V> choiceMap(
    key: Par<K>,
    choices: Map<K, Par<V>>
): Par<V> = { es: ExecutorService ->
    run(es, choices.getValue(run(es, key).get()))
}