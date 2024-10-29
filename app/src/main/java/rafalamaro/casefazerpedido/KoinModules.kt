package rafalamaro.casefazerpedido

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::PlaceOrderViewModel)
}