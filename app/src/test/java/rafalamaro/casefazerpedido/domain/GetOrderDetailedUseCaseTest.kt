package rafalamaro.casefazerpedido.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import rafalamaro.casefazerpedido.data.datasource.IOrdersHistoryLocalDatasource
import rafalamaro.casefazerpedido.domain.implementations.GetOrderDetailedUseCaseImpl
import rafalamaro.casefazerpedido.mocks.orderModelMock

class GetOrderDetailedUseCaseTest {
    @MockK
    private lateinit var orderHistoryLocalDatasource: IOrdersHistoryLocalDatasource

    private lateinit var getOrderDetailedUseCase: GetOrderDetailedUseCaseImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        getOrderDetailedUseCase = GetOrderDetailedUseCaseImpl(orderHistoryLocalDatasource)
    }

    @Test
    fun `given an order number, should return a order model`() = runTest {
        // Given
        val orderNumber = 123
        coEvery { orderHistoryLocalDatasource.getOrderDetailed(any()) } returns orderModelMock

        // When
        val result = getOrderDetailedUseCase.getOrderDetailed(orderNumber)

        // Then
        assertEquals(orderModelMock, result)
    }
}
