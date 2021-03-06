package konflin.converter

import kotlin.reflect.KClass

sealed class ConfigType<out Type : Any>(val clazz: KClass<out Type>) {
    companion object Types {
        object String : ConfigType<kotlin.String>(kotlin.String::class)
        object Int : ConfigType<kotlin.Int>(kotlin.Int::class)
        object Boolean : ConfigType<kotlin.Boolean>(kotlin.Boolean::class)
    }
}
