package org.ei.telemedicine.view.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.ei.telemedicine.sync.SyncAfterFetchListener;
import org.ei.telemedicine.sync.SyncProgressIndicator;
import org.ei.telemedicine.sync.UpdateActionsTask;
import org.ei.telemedicine.util.Log;
import org.ei.telemedicine.view.activity.ActionActivity;
import org.ei.telemedicine.view.activity.LoginActivity;

import java.util.ArrayList;

import static org.ei.telemedicine.util.Log.logInfo;

public class SyncBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        logInfo("Sync alarm triggered. Trying to Sync.");
        if (!ActionActivity.isBusy)
            LoginActivity.disconnectWS();

        try {
            if (LoginActivity.mConnection == null || !LoginActivity.mConnection.isConnected())
                LoginActivity.connectWS();
        } catch (Exception e) {
            LoginActivity.disconnectWS();
        }
        UpdateActionsTask updateActionsTask = new UpdateActionsTask(
                context,
                org.ei.telemedicine.Context.getInstance().actionService(),
                org.ei.telemedicine.Context.getInstance().formSubmissionSyncService(), new SyncProgressIndicator());
        try {
            final ArrayList<String> villagesList = org.ei.telemedicine.Context.getInstance().allSettings().getVillages();
            for (String villageName : villagesList)
                updateActionsTask.updateFromServer(new SyncAfterFetchListener(), villageName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

