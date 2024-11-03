package rafalamaro.casefazerpedido

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import rafalamaro.casefazerpedido.database.AppDatabase
import rafalamaro.casefazerpedido.database.DatabaseProvider
import rafalamaro.casefazerpedido.datasource.IOrdersHistoryLocalDatasource
import rafalamaro.casefazerpedido.datasource.OrdersHistoryLocalDatasourceImpl
import rafalamaro.casefazerpedido.viewmodels.MainScreenViewModel
import rafalamaro.casefazerpedido.viewmodels.OrderHistoryListViewModel
import rafalamaro.casefazerpedido.viewmodels.PlaceOrderViewModel

val appModule = module {
    single {
        DatabaseProvider.getDatabase(get())
    }

    single {
        get<AppDatabase>().ordersHistoryDao()
    }

    single<IOrdersHistoryLocalDatasource> {
        OrdersHistoryLocalDatasourceImpl(get())
    }

    single<CoroutineDispatcher> { Dispatchers.IO }

    viewModelOf(::PlaceOrderViewModel)
    viewModelOf(::OrderHistoryListViewModel)
    viewModelOf(::MainScreenViewModel)
}