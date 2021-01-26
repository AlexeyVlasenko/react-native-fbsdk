/*
 * Copyright (c) Facebook, Inc. and its affiliates.
 * All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.reactnative.androidsdk;

import androidx.annotation.Nullable;

import com.facebook.FacebookSdk;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

/**
 * This is a {@link NativeModule} that allows JS to use SDK settings in Facebook Android SDK.
 */
@ReactModule(name = FBSettingsModule.NAME)
public class FBSettingsModule extends BaseJavaModule {
    private final static String ERR_FACEBOOK_MISCONFIGURED = "ERR_FACEBOOK_MISCONFIGURED";

    public static final String NAME = "FBSettings";

    public FBSettingsModule() {}

    @Override
    public String getName() {
        return NAME;
    }


      @ReactMethod
      public void setAutoInitEnabledAsync(final Boolean enabled, final Promise promise) {
        FacebookSdk.setAutoInitEnabled(enabled);
        if (enabled) {
          FacebookSdk.fullyInitialize();
        }
        promise.resolve(null);
      }

      @ExpoMethod
        public void initializeAsync(ReadableArguments options, final Promise promise) {
          final String appId = options.getString("appId");

          try {
            if (appId != null) {
              mAppId = appId;
              FacebookSdk.setApplicationId(appId);
            }
            if (options.containsKey("appName")) {
              mAppName = options.getString("appName");
              FacebookSdk.setApplicationName(mAppName);
            }
            if (options.containsKey("version")) {
              FacebookSdk.setGraphApiVersion(options.getString("version"));
            }
            if (options.containsKey("autoLogAppEvents")) {
              Boolean autoLogAppEvents = options.getBoolean("autoLogAppEvents");
              FacebookSdk.setAutoLogAppEventsEnabled(autoLogAppEvents);
            }
            if (options.containsKey("domain")) {
              FacebookSdk.setFacebookDomain(options.getString("domain"));
            }
            if (options.containsKey("isDebugEnabled")) {
              FacebookSdk.setIsDebugEnabled(options.getBoolean("isDebugEnabled"));
            }

            FacebookSdk.sdkInitialize(getContext(), new FacebookSdk.InitializeCallback() {
              @Override
              public void onInitialized() {
                FacebookSdk.fullyInitialize();
                mAppId = FacebookSdk.getApplicationId();
                mAppName = FacebookSdk.getApplicationName();
                promise.resolve(null);
              }
            });
          } catch (Exception e) {
            promise.reject(ERR_FACEBOOK_MISCONFIGURED, "An error occurred while trying to initialize a FBSDK app", e);
          }
        }

    /**
     * Sets data processing options
     * @param options list of the options
     */
    @ReactMethod
    public void setDataProcessingOptions(@Nullable String[] options) {
        FacebookSdk.setDataProcessingOptions(options, 0, 0);
    }

    /**
     * Sets data processing options with country and state
     * @param options list of the options
     * @param country code of the country
     * @param state code of the state
     */
    @ReactMethod
    public static void setDataProcessingOptionsExtra(@Nullable String[] options, int country, int state) {
        FacebookSdk.setDataProcessingOptions(options, country, state);
    }
}
