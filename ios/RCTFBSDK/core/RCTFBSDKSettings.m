/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 * All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE file in the root directory of this source tree.
 */

#import "RCTFBSDKSettings.h"

#import <React/RCTConvert.h>

@implementation RCTFBSDKSettings

RCT_EXPORT_MODULE(FBSettings);

- (dispatch_queue_t)methodQueue
{
  return dispatch_get_main_queue();
}

#pragma mark - React Native Methods


RCT_EXPORT_METHOD(setDebug:(BOOL)ATE resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
{
  [FBSDKSettings enableLoggingBehavior:FBSDKLoggingBehaviorAppEvents];
  resolve(@(true));
}

RCT_EXPORT_METHOD(isInitialized:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
{
  resolve(@(true));
}

RCT_EXPORT_METHOD(getAutoInitEnabledAsync:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
{
  resolve(@(true));
}

RCT_EXPORT_METHOD(setAutoInitEnabledAsync:(BOOL)ATE resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
{
  [FBSDKSettings setAutoInitEnabled:ATE];
  resolve(@(true));
}

RCT_EXPORT_METHOD(initializeAsync:(NSString *)appId appName:(NSString *)appName
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
{
  if (appId) {
    [FBSDKSettings setAppID:appId];
  }
  if (appName) {
    [FBSDKSettings setDisplayName:appName];
  }

  [FBSDKApplicationDelegate initializeSDK:nil];
  [FBSDKAppEvents activateApp];

  resolve(nil);
}



RCT_EXPORT_METHOD(getAdvertiserTrackingEnabled:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
{
  BOOL ATE = [FBSDKSettings isAdvertiserTrackingEnabled];
  resolve(@(ATE));
}

RCT_EXPORT_METHOD(setAdvertiserTrackingEnabled:(BOOL)ATE resolver:(RCTPromiseResolveBlock)resolve rejector:(RCTPromiseRejectBlock)reject)
{
  BOOL result = [FBSDKSettings setAdvertiserTrackingEnabled:ATE];
  resolve(@(result));
}

RCT_EXPORT_METHOD(setDataProcessingOptions:(nullable NSStringArray *)options)
{
  [FBSDKSettings setDataProcessingOptions:options];
}

RCT_EXPORT_METHOD(setDataProcessingOptions:(nullable NSStringArray *)options country:(int)country state:(int)state)
{
  [FBSDKSettings setDataProcessingOptions:options country:country state:state];
}

@end
