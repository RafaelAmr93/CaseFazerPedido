package rafalamaro.casefazerpedido.domain.implementations

import rafalamaro.casefazerpedido.data.datasource.IOrdersHistoryLocalDatasource
import rafalamaro.casefazerpedido.data.model.BaseOrderHistoryModel
import rafalamaro.casefazerpedido.domain.contracts.IGetOrderHistoryListUseCase

class GetOrderHistoryListUseCaseImpl(
    private val orderHistoryLocalDatasource: IOrdersHistoryLocalDatasource
) : IGetOrderHistoryListUseCase {
    override suspend fun getOrderHistoryList(): List<BaseOrderHistoryModel> {
        return orderHistoryLocalDatasource.getOrderHistoryList()
    }
}