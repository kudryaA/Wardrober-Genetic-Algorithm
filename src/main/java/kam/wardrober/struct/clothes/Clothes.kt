package kam.wardrober.struct.clothes

import kam.wardrober.struct.Temperature

abstract class Clothes {
    var name: String? = null
    var temperature: Temperature? = null
    var target: String? = null
    var colors: List<String>? = null
    var comfortable: Short? = null

    override fun toString(): String {
        return "Clothes(name=$name, temperature=$temperature, target=$target, colors=$colors, comfortable=$comfortable)"
    }


}