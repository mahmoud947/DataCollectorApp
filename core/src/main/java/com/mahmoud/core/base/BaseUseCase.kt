package com.mahmoud.core.base

interface BaseUseCase {
    operator fun invoke()
}

interface BaseIOUseCase<in IN, out OUT> {
    operator fun invoke(input: IN): OUT
}

interface BaseIUseCase<in IN> {
    operator fun invoke(input: IN)
}

interface BaseOUseCase< out OUT> {
    operator fun invoke(): OUT
}
