package kam.wardrober.instructions

import kam.wardrober.struct.clothes.Clothes
import kam.wardrober.struct.clothes.Set

fun solveColor(set: Set): Double {
    val buf = listOf(getValue(set.bottomClothes), getValue(set.footwear),
            getValue(set.topClothes), getValue(set.headdress))

    var res = 0.0

    for (i in 0 until buf.size) {
        for (j in i + 1 until buf.size) {
            res += checkColor(buf[i], buf[j])
        }
    }

    return res
}

private fun getValue(clothes: Clothes?): List<String> {
    return try {
        clothes!!.colors!!
    } catch (e: KotlinNullPointerException) {
        ArrayList()
    }
}


private fun checkColor(v1: List<String>, v2: List<String>): Int {
    var res = 0
    val n = 5

    val mas = arrayOf(intArrayOf(n, 0, n, n, n, n, n, 0, 0, 0, n), // green
                                    intArrayOf(0, n, n, n, n, 0, n, 0, n, 0, n), // red
                                    intArrayOf(n, n, n, n, n, n, n, n, n, n, n), // black
                                    intArrayOf(n, n, n, n, n, n, n, n, n, n, n), // white
                                    intArrayOf(n, n, n, n, n, n, n, 0, 0, 0, 0), // yellow
                                    intArrayOf(0, 0, n, n, 0, n, 0, 0, 0, 0, 0), // orange
                                    intArrayOf(0, n, n, n, 0, 0, n, 0, n, 0, n), // blue
                                    intArrayOf(0, 0, n, n, 0, n, 0, n, 0, n, n), // violet
                                    intArrayOf(n, n, n, n, n, n, n, n, n, 0, n), // brown
                                    intArrayOf(0, 0, n, n, 0, 0, n, n, 0, n, 0), // pink
                                    intArrayOf(n, n, n, n, 0, 0, n, n, n, 0, n)  // gray
    )

    for (i in 0 until v1.size) {
        for (j in 0 until v2.size) {
            if (mas[i][j] > res) {
                res = mas[i][j]
                break
            }
        }
    }

    return res
}