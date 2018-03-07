package com.github.fkrull.konflin.typedescriptors.impl

import com.github.fkrull.konflin.typedescriptors.ConfigType
import com.github.fkrull.konflin.typedescriptors.TypeDescriptor
import kotlin.reflect.KClass

internal class IdentityTypeDescriptor<Type : Any>(override val configType: ConfigType<Type>) : TypeDescriptor<Type, Type> {
    override val clazz: KClass<out Type> = configType.clazz
    override fun fromConfig(value: Type): Type = value
}
