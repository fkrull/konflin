package com.github.fkrull.konflin.impl

import kotlin.reflect.KClass

internal actual data class KClassId actual constructor(val value: Any)

internal actual val <T : Any> KClass<T>.id: KClassId get() = KClassId(this)
