package kam.wardrober.struct.clothes

import kam.wardrober.instructions.solveColor
import kam.wardrober.instructions.solveComfortable
import kam.wardrober.instructions.solveTarget
import kam.wardrober.instructions.solveTemperature
import kam.wardrober.struct.Temperature

class Set(var footwear: Footwear?, var bottomClothes: BottomClothes?,
          var topClothes: TopClothes?, var headdress: Headdress?) {

    fun getMark(currentTemperature: Temperature): Double {
        val color = solveColor(this)
        val comfortable = solveComfortable(this)
        val targetRes = solveTarget(this)
        val temperature = solveTemperature(this, currentTemperature)
        return color + comfortable + targetRes + temperature
    }

    override fun toString(): String {
        return "Set(footwear=$footwear, bottomClothes=$bottomClothes, topClothes=$topClothes, headdress=$headdress)"
    }


}