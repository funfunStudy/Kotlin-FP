package funfun.study.kfp.ps


class Golf1 {
    fun solution(matrix: Array<IntArray>, r: Int): Array<IntArray> {
        val degree = r % 4
        if (degree == 0) return matrix

        var answer: Array<IntArray> = matrix
        for (i in 1..degree) {
            answer = rotate(answer)
        }
        return answer
    }

    private fun rotate(matrix: Array<IntArray>): Array<IntArray> {
        val result = Array(matrix.size) { IntArray(matrix[0].size) }

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                result[j][matrix.size - 1 - i] = matrix[i][j];
            }
        }

        return result
    }
}

fun main() {
    val g = Golf1()
    val result = g.solution(
        arrayOf(
            intArrayOf(4, 1, 2),
            intArrayOf(7, 3, 4),
            intArrayOf(3, 5, 6),
        ), 3
    )

    for (r in result) {
        println(r.contentToString())
    }
}