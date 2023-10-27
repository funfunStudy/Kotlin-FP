package funfun.study.kfp.chap7

class Par<ITEM>(val get: ITEM)

fun <ITEM> get(item: Par<ITEM>): ITEM = item.get

fun <ITEM> unit(item: ITEM): Par<ITEM> = Par(item)

fun <ITEM> lazyUnit(item: () -> ITEM): Par<ITEM> = fork { unit(item()) }
