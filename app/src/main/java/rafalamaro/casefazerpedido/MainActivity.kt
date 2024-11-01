package rafalamaro.casefazerpedido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.core.context.GlobalContext.startKoin
import rafalamaro.casefazerpedido.ui.theme.CaseFazerPedidoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            modules(appModule)
        }
        setContent {
            CaseFazerPedidoTheme {
                PlaceOrderScreen()
            }
        }
    }
}
