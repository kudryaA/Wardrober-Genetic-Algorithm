package kam.wardrober.instructions

import kam.wardrober.struct.clothes.Set

fun solveComfortable (set: Set): Double {

    var bottom: Short? = null
    var foot: Short? = null
    var head: Short? = null
    var top: Short? = null

    try {
        bottom = set.bottomClothes!!.comfortable
    } catch (e: KotlinNullPointerException) {   }

    try {
        foot = set.footwear!!.comfortable
    } catch (e: KotlinNullPointerException) {   }

    try {
        head = set.headdress!!.comfortable
    } catch (e: KotlinNullPointerException) {   }

    try {
        top = set.topClothes!!.comfortable
    } catch (e: KotlinNullPointerException) {   }

    val list = listOf(bottom, foot, head, top)
    var res =  0.0
    list.filter { item ->
        item != null
    }.forEach {
        res += it!!
    }

    return res
}