package kam.wardrober.struct

/**
 * @author Anton Kudryavtsev
 */

class Temperature(var l: Double, var h: Double) {
    var isRain = false

    fun isDegree(v: Int): Boolean {
        val min = Math.min(l, h)
        val max = Math.max(l, h)
        return v in min..max
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Temperature

        if (l != other.l) return false
        if (h != other.h) return false
        if (isRain != other.isRain) return false

        return true
    }

    override fun hashCode(): Int {
        var result = l.hashCode()
        result = 31 * result + h.hashCode()
        result = 31 * result + isRain.hashCode()
        return result
    }

    override fun toString(): String {
        return "Temperature(l=$l, h=$h, isRain=$isRain)"
    }


}