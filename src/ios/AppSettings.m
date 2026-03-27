#import <Cordova/CDV.h>

@interface AppSettings : CDVPlugin
- (void)open:(CDVInvokedUrlCommand*)command;
@end

@implementation AppSettings

- (void)open:(CDVInvokedUrlCommand*)command {

    NSURL *url = [NSURL URLWithString:UIApplicationOpenSettingsURLString];

    if ([[UIApplication sharedApplication] canOpenURL:url]) {
        [[UIApplication sharedApplication] openURL:url options:@{} completionHandler:nil];
    }

    CDVPluginResult *result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
}

@end