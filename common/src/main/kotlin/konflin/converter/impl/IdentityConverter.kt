package konflin.converter.impl

import konflin.converter.ConfigType
import konflin.converter.Converter

internal class IdentityConverter<Type : Any>(override val inType: ConfigType<Type>) : Converter<Type, Type> {
    override val outType = inType.clazz
    override fun fromConfig(value: Type): Type = value
}
