package rafalamaro.casefazerpedido.domain.contracts

import kotlinx.coroutines.flow.Flow

interface IGetOrdersCountUseCase {
    suspend fun getOrdersCount(): Flow<Int?>
}