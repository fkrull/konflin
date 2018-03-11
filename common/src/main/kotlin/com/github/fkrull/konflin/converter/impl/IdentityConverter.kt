package com.github.fkrull.konflin.converter.impl

import com.github.fkrull.konflin.converter.ConfigType
import com.github.fkrull.konflin.converter.Converter

internal class IdentityConverter<Type : Any>(override val inType: ConfigType<Type>) : Converter<Type, Type> {
    override fun fromConfig(value: Type): Type = value
}
