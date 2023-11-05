package funfun.study.kfp.chap7

fun <A, B> chooser(pa: Par<A>, choices: (A) -> Par<B>): Par<B> = { es: ExecutorService ->
    run(es, choices(run(es, pa).get()))
}

fun <ITEM> choice_2(
    cond: Par<Boolean>,
    t: Par<ITEM>,
    f: Par<ITEM>
): Par<ITEM> = chooser(cond) {
    when (it) {
        true -> t
        false -> f
    }
}

fun <ITEM> choiceN_2(
    cond: Par<Int>,
    choices: List<Par<ITEM>>
): Par<ITEM> = chooser(cond) {
    choices[it]
}

fun <K, V> choiceMap_2(
    cond: Par<K>,
    choices: Map<K, Par<V>>
): Par<V> = chooser(cond) {
    choices.getValue(it)
}