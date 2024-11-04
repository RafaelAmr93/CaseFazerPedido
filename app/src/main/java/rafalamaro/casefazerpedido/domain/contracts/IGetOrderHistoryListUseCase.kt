package rafalamaro.casefazerpedido.domain.contracts

import rafalamaro.casefazerpedido.data.model.BaseOrderHistoryModel

interface IGetOrderHistoryListUseCase {
    suspend fun getOrderHistoryList(): List<BaseOrderHistoryModel>
}
