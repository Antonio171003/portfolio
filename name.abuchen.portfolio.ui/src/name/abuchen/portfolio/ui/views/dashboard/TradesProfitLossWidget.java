package name.abuchen.portfolio.ui.views.dashboard;

import java.text.MessageFormat;
import java.util.List;

import name.abuchen.portfolio.model.Dashboard.Widget;
import name.abuchen.portfolio.money.Money;
import name.abuchen.portfolio.money.MoneyCollectors;
import name.abuchen.portfolio.money.Values;
import name.abuchen.portfolio.snapshot.trades.Trade;
import name.abuchen.portfolio.ui.views.trades.TradeDetailsView;
import name.abuchen.portfolio.util.TextUtil;

public class TradesProfitLossWidget extends AbstractTradesWidget
{
    public TradesProfitLossWidget(Widget widget, DashboardData dashboardData)
    {
        super(widget, dashboardData);
        addConfig(new CostMethodConfig(this));
    }

    @Override
    public void update(TradeDetailsView.Input input)
    {
        this.title.setText(TextUtil.tooltip(getWidget().getLabel()));

        List<Trade> trades = input.getTrades();
        boolean useFifo = get(CostMethodConfig.class).getValue().useFifo();

        Money profitLoss = useFifo
                        ? trades.stream().map(Trade::getProfitLoss)
                                        .collect(MoneyCollectors.sum(
                                                        getDashboardData().getCurrencyConverter().getTermCurrency()))
                        : trades.stream().map(Trade::getProfitLossMovingAverage)
                        .collect(MoneyCollectors.sum(getDashboardData().getCurrencyConverter().getTermCurrency()));

        this.indicator.setText(
                        MessageFormat.format(profitLoss.isNegative() ? "<red>{0}</red>" : "<green>{0}</green>", //$NON-NLS-1$ //$NON-NLS-2$
                                        Values.Money.format(profitLoss, getClient().getBaseCurrency())));
    }
}
