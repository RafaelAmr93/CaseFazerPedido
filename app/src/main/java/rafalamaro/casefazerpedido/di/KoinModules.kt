package rafalamaro.casefazerpedido.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import rafalamaro.casefazerpedido.data.database.AppDatabase
import rafalamaro.casefazerpedido.data.database.DatabaseProvider
import rafalamaro.casefazerpedido.data.datasource.IOrdersHistoryLocalDatasource
import rafalamaro.casefazerpedido.data.datasource.OrdersHistoryLocalDatasourceImpl
import rafalamaro.casefazerpedido.domain.contracts.IGetOrderDetailedUseCase
import rafalamaro.casefazerpedido.domain.contracts.IGetOrderHistoryListUseCase
import rafalamaro.casefazerpedido.domain.implementations.GetOrdersCountUseCaseImpl
import rafalamaro.casefazerpedido.domain.implementations.GetTotalSalesUseCaseImpl
import rafalamaro.casefazerpedido.domain.contracts.IGetOrdersCountUseCase
import rafalamaro.casefazerpedido.domain.contracts.IGetTotalSalesUseCase
import rafalamaro.casefazerpedido.domain.contracts.IInsertOrderUseCase
import rafalamaro.casefazerpedido.domain.implementations.GetOrderDetailedUseCaseImpl
import rafalamaro.casefazerpedido.domain.implementations.GetOrderHistoryListUseCaseImpl
import rafalamaro.casefazerpedido.domain.implementations.IInsertOrderUseCaseImpl
import rafalamaro.casefazerpedido.ui.viewmodels.MainScreenViewModel
import rafalamaro.casefazerpedido.ui.viewmodels.OrderHistoryListViewModel
import rafalamaro.casefazerpedido.ui.viewmodels.PlaceOrderViewModel

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

    factory<IGetTotalSalesUseCase> {
        GetTotalSalesUseCaseImpl(get())
    }

    factory<IGetOrdersCountUseCase> {
        GetOrdersCountUseCaseImpl(get())
    }

    factory<IGetOrderHistoryListUseCase> {
        GetOrderHistoryListUseCaseImpl(get())
    }

    factory<IGetOrderDetailedUseCase> {
        GetOrderDetailedUseCaseImpl(get())
    }

    factory<IInsertOrderUseCase> {
        IInsertOrderUseCaseImpl(get())
    }

    viewModelOf(::PlaceOrderViewModel)
    viewModelOf(::OrderHistoryListViewModel)
    viewModelOf(::MainScreenViewModel)
}