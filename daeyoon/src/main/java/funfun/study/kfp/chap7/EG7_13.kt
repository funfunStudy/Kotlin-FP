package funfun.study.kfp.chap7

fun <ITEM> join(item: Par<Par<ITEM>>): Par<ITEM> = { es: ExecutorService ->
    run(es, run(es, item).get())
}

fun <ITEM, RESULT> flatMap(item: Par<ITEM>, block: (ITEM) -> Par<RESULT>): Par<RESULT> =
    join(map(item, block))