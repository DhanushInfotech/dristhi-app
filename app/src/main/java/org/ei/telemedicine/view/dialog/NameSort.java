package org.ei.telemedicine.view.dialog;

import org.ei.telemedicine.Context;
import org.ei.telemedicine.R;
import org.ei.telemedicine.view.contract.SmartRegisterClients;

import java.util.Collections;

import static org.ei.telemedicine.view.contract.SmartRegisterClient.NAME_COMPARATOR;

public class NameSort implements SortOption {
    @Override
    public String name() {
        return Context.getInstance().getStringResource(R.string.sort_by_name_label);
    }

    @Override
    public SmartRegisterClients sort(SmartRegisterClients allClients) {
        Collections.sort(allClients, NAME_COMPARATOR);
        return allClients;
    }
}
