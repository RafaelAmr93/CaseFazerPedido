package rafalamaro.casefazerpedido.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import rafalamaro.casefazerpedido.data.datasource.IOrdersHistoryLocalDatasource
import rafalamaro.casefazerpedido.domain.implementations.GetOrderHistoryListUseCaseImpl
import rafalamaro.casefazerpedido.mocks.baseOrderHistoryMock

class GetOrderHistoryListUseCaseTest {
    @MockK
    private lateinit var orderHistoryLocalDatasource: IOrdersHistoryLocalDatasource

    private lateinit var getOrderDetailedUseCase: GetOrderHistoryListUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        getOrderDetailedUseCase = GetOrderHistoryListUseCaseImpl(orderHistoryLocalDatasource)
    }

    @Test
    fun `should return a list of base order history model`() = runTest {
        // Given
        val list = listOf(baseOrderHistoryMock)
        coEvery { orderHistoryLocalDatasource.getOrderHistoryList() } returns list

        // When
        val result = getOrderDetailedUseCase.getOrderHistoryList()

        // Then
        assertEquals(list, result)
    }
}