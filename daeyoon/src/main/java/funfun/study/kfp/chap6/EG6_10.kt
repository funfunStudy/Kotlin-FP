package funfun.study.kfp.chap6

import funfun.study.kfp.chap3.Cons
import funfun.study.kfp.chap3.Nil
import funfun.study.kfp.chap3.foldRight
import funfun.study.kfp.chap3.List

fun <STATE, ITEM> unitS(item: ITEM): State<STATE, ITEM> = State { state ->
    item to state
}

fun <STATE, ITEM, RESULT> flatMapS(f: State<STATE, ITEM>, g: (ITEM) -> State<STATE, RESULT>): State<STATE, RESULT> =
   State { state ->
       val (item, state1) = f.run(state)
       g(item).run((state1))
   }

fun <STATE, ITEM, RESULT> mapS(s: State<STATE, ITEM>, f: (ITEM) -> RESULT): State<STATE, RESULT> =
    flatMapS(s) { item ->
        unitS(f(item))
    }

fun <STATE, ITEM1, ITEM2, RESULT> map2S(s1: State<STATE, ITEM1>, s2: State<STATE, ITEM2>, f: (ITEM1, ITEM2) -> RESULT): State<STATE, RESULT> =
    flatMapS(s1) { item1 ->
        mapS(s2) { item2 ->
            f(item1, item2)
        }
    }

fun <STATE, ITEM> sequenceS(fs: List<State<STATE, ITEM>>): State<STATE, List<ITEM>> =
    foldRight(fs, unitS<STATE, List<ITEM>>(Nil)) { item, acc ->
        map2S(item, acc) { h, t ->
            Cons(h, t)
        }
    }