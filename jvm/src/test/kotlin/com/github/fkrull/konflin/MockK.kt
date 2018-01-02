package com.github.fkrull.konflin

import io.mockk.MockKMatcherScope
import io.mockk.MockKStubScope
import io.mockk.every
import io.mockk.mockk

actual inline fun <reified T : Any> mockk(): T = mockk()
actual inline fun <T> every(noinline stubBlock: MockKMatcherScope.() -> T): MockKStubScope<T> = every(stubBlock)
