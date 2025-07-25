package name.abuchen.portfolio.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Link;

import name.abuchen.portfolio.ui.Messages;
import name.abuchen.portfolio.ui.UIConstants;
import name.abuchen.portfolio.ui.util.DesktopAPI;

public class CoingeckoPreferencePage extends FieldEditorPreferencePage
{

    public CoingeckoPreferencePage()
    {
        super(GRID);

        setTitle("CoinGecko"); //$NON-NLS-1$
    }

    @Override
    public void createFieldEditors()
    {
        addField(new DescriptionFieldEditor(Messages.PrefDescriptionCoinGeckoDemoAPIKey, getFieldEditorParent()));

        addField(new StringFieldEditor(UIConstants.Preferences.COINGECKO_DEMO_API_KEY, //
                        Messages.PrefTitleDemoAPIKey, getFieldEditorParent()));

        // configure paid subscriptions by CoinGecko

        addField(new DescriptionFieldEditor(Messages.PrefDescriptionCoingecko, getFieldEditorParent()));

        Link link = new Link(getFieldEditorParent(), SWT.NONE);
        link.setText("<a>https://www.coingecko.com/en/developers/dashboard</a>"); //$NON-NLS-1$
        link.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 3, 1));
        link.addSelectionListener(SelectionListener.widgetSelectedAdapter(
                        e -> DesktopAPI.browse("https://www.coingecko.com/en/developers/dashboard"))); //$NON-NLS-1$

        addField(new StringFieldEditor(UIConstants.Preferences.COINGECKO_API_KEY, //
                        Messages.PrefTitleAPIKey, getFieldEditorParent()));
    }
}
