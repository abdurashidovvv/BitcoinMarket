package com.mertceyhan.bitcoinmarket.widget

import androidx.compose.runtime.Composable
import androidx.datastore.preferences.core.Preferences
import androidx.glance.LocalContext
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.core.theme.BitcoinMarketTheme
import com.mertceyhan.bitcoinmarket.utils.extensions.isSystemInDarkTheme
import com.mertceyhan.bitcoinmarket.widget.receiver.MarketWidgetReceiver
import com.mertceyhan.bitcoinmarket.widget.ui.BitcoinWidget
import com.mertceyhan.bitcoinmarket.widget.ui.BitcoinWidgetUiState

class MarketWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    @Composable
    override fun Content() {

        val context = LocalContext.current
        val prefs = currentState<Preferences>()
        val currentPrice = prefs[MarketWidgetReceiver.currentPrice]
        val changeRate = prefs[MarketWidgetReceiver.changeRate]
        val isChangeRatePositive = prefs[MarketWidgetReceiver.isChangeRatePositive]

        val bitcoinWidgetUiState = BitcoinWidgetUiState(
            currentPrice = currentPrice ?: context.getString(R.string.fetch_alert_label),
            changeRate = changeRate ?: context.getString(R.string.empty_rate),
            isChangeRatePositive = isChangeRatePositive ?: true
        )

        BitcoinMarketTheme(isSystemInDarkTheme = context.isSystemInDarkTheme()) {
            BitcoinWidget(bitcoinWidgetUiState)
        }
    }
}