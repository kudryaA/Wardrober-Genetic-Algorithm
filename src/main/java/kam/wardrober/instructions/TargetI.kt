package kam.wardrober.instructions

import kam.wardrober.struct.clothes.Clothes
import kam.wardrober.struct.clothes.Set
import kam.wardrober.struct.enums.Target

fun solveTarget(set: Set): Double {
    var mark = 0
    val buf = listOf(getValue(set.bottomClothes), getValue(set.footwear),
                                    getValue(set.topClothes), getValue(set.headdress))

    mark += rule(buf[0], buf[1])
    mark += rule(buf[1], buf[2])
    mark += rule(buf[2], buf[3])
    mark += rule(buf[3], buf[0])

    return 10 * mark.toDouble()
}

private fun rule(s1: String?, s2: String?): Int {
    if (or(s1, s2, Target.PARTY.value, Target.PARTY.value))
        return 2
    if (or(s1, s2, Target.PARTY.value, Target.WALK.value))
        return 1
    if (or(s1, s2, Target.WALK.value, Target.WALK.value))
        return 2
    if (or(s1, s2, Target.WALK.value, Target.WORK.value))
        return 1
    if (or(s1, s2, Target.WORK.value, Target.WORK.value))
        return 2

    return 0
}

private fun getValue(clothes: Clothes?): String? {
    return try {
         clothes!!.target
    } catch (e: KotlinNullPointerException) {
        ""
    }
}

private fun or(s1: String?, s2: String?, r1: String, r2: String): Boolean {
    val b1 = s1 == r1 && s2 ==r2
    val b2 = s1 == r2 && s2 == r1
    return b1 || b2
}