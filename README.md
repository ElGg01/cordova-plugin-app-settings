# cordova-plugin-app-settings
Simple Cordova plugin to open the app settings and, on Android, also open the notification settings directly. Designed for developers who need a lightweight solution, with room for future expansion.

## Installation

### Install from a local path

1. Download or clone this repository.
2. Run the following command inside your Cordova project:

```bash
cordova plugin add <plugin-path>
```

Example:
```bash
cordova plugin add ../../cordova-plugin-app-settings
```

## Usage
Once running in a Cordova environment, the plugin is available via the global AppSettings object.

Currently, the plugin provides two methods:

### open (Android & iOS)
Opens the application settings.

Example:
```js
AppSettings.open(
    () => console.log("Settings opened."),
    (err) => console.error("Error opening settings: ", err)
);
```

### openNotifications (Android only)
Opens the app's notification settings directly.

Example:
```js
AppSettings.openNotifications(
    () => console.log("Notification settings opened."),
    (err) => console.error("Error opening notification settings:", err)
);
```

**Notes:**
- Android 8 and above: Opens the app’s notification settings.
- Android 5 to 7: Opens the app’s notification settings using a non-official workaround.
- Android < 5: Open app settings like the open method.