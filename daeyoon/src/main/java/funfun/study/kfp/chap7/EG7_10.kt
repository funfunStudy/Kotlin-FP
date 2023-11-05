package funfun.study.kfp.chap7

fun <ITEM> choice(cond: Par<Boolean>, t: Par<ITEM>, f: Par<ITEM>): Par<ITEM> = { es: ExecutorService ->
    when (run(es, cond).get()) {
        true -> t(es)
        false -> f(es)
    }
}

fun <ITEM> choiceN(cond: Par<Int>, choices: List<Par<ITEM>>): Par<ITEM> = { es: ExecutorService ->
    run(es, choices[run(es, cond).get()])
}