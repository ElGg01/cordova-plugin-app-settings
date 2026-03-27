var exec = require("cordova/exec");

exports.open = function (success, error) {
  exec(success, error, "AppSettings", "open", []);
};

exports.openNotifications = function (success, error) {
  exec(success, error, "AppSettings", "openNotifications", []);
};
