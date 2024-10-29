package rafalamaro.casefazerpedido

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rafalamaro.casefazerpedido.ui.theme.Typography

@Composable
internal fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Header()
        Body()
        Footer()
    }
}

@Composable
private fun BoxScope.Header() {
    Column(
        modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(
                top = 32.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = 24.dp
            )
    ) {
        Text(
            text = stringResource(R.string.main_screen_title),
            style = Typography.titleMedium,
        )
        HorizontalDivider(
            thickness = 2.dp
        )
    }
}

@Composable
private fun BoxScope.Body() {
    Column(
        modifier = Modifier
            .align(Alignment.Center)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        TotalOrders()
        TotalSales()
    }
}

@Composable
private fun BoxScope.Footer() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 40.dp
            )
    ) {
        FooterButtons(
            text = stringResource(R.string.order_history_button_label),
            onClick = {}
        )
        FooterButtons(
            text = stringResource(R.string.place_order_button_label),
            onClick = {}
        )
    }
}

@Composable
private fun TotalOrders() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val tempNumber = 10
        Text(
            text = stringResource(R.string.total_orders),
            style = Typography.titleMedium
        )
        Text(
            text = stringResource(R.string.order_symbol, tempNumber),
            fontSize = 50.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        HorizontalDivider(
            thickness = 2.dp
        )
    }
}

@Composable
private fun TotalSales() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val tempNumber = 10
        Text(
            text = stringResource(R.string.total_sales),
            style = Typography.titleMedium
        )
        Text(
            text = stringResource(R.string.money_symbol, tempNumber),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 50.sp
        )
        HorizontalDivider(
            thickness = 2.dp
        )
    }
}

@Composable
private fun FooterButtons(
    text: String,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .background(Color.LightGray)
            .clickable(
                onClick = { onClick() },
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Text(
            text = text,
            style = Typography.titleMedium,
            modifier = Modifier.padding(vertical = 12.dp)
        )
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen()
}