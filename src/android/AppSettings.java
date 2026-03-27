package com.elgg01.appsettings;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class AppSettings extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("open")) {
            this.openSettings(callbackContext);
            return true;
        }

        if (action.equals("openNotifications")) {
            this.openNotificationSettings(callbackContext);
            return true;
        }

        return false;
    }

    private void openSettings(CallbackContext callbackContext) {
        try {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", cordova.getActivity().getPackageName(), null);
            intent.setData(uri);
            cordova.getActivity().startActivity(intent);

            callbackContext.success();
        } catch (Exception e) {
            callbackContext.error("Error opening settings: " + e.getMessage());
        }
    }

    private void openNotificationSettings(CallbackContext callbackContext) {
        try {
            Intent intent;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                // Android 8+ → pantalla directa de notificaciones
                intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, cordova.getActivity().getPackageName());
            } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                // Android 5-7 → pantalla de notificaciones de la app
                intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra("app_package", cordova.getActivity().getPackageName());
                intent.putExtra("app_uid", cordova.getActivity().getApplicationInfo().uid);
            }
            else {
                // Android < 5  → Fallback → ajustes generales de la app
                intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", cordova.getActivity().getPackageName(), null);
                intent.setData(uri);
            }

            cordova.getActivity().startActivity(intent);
            callbackContext.success();

        } catch (Exception e) {
            callbackContext.error("Error opening notification settings: " + e.getMessage());
        }
    }
}