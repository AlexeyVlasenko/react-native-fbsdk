import React, {memo, useEffect} from 'react';
import { View, StyleSheet, Button } from 'react-native';
import PropTypes from 'prop-types';
import {Settings as FBSDK, AppEventsLogger} from 'react-native-fbsdk';

const App = () => {

  useEffect(()=> {
    const init = async ()=> {
      //
      // await FBSDK.setDebug(true);
      await FBSDK.setAutoInitEnabledAsync(false);
    };

    init();
  },[]);

  return (
    <View style={styles.container}>
      <Button title={'DO_INIT'} onPress={async ()=> {
        await FBSDK.initializeAsync('1312899995746379', 'Test App');
        await FBSDK.setAutoInitEnabledAsync(true);
      }}/>
      <Button title={'IS_INITED'} onPress={async ()=> {
        const res = await FBSDK.isInitialized();
        console.warn(res);
      }}/>
      <Button title={'IS_AUTO_INIT_ENABLED'} onPress={async ()=> {
        const res = await FBSDK.getAutoInitEnabledAsync();
        console.warn(res);
      }}/>
      <Button title={'ANALYTIC'} onPress={async ()=> {
        const res = await AppEventsLogger.logEvent('ANALYTIC_PRESSED_4');
        console.warn(res);
      }}/>
    </View>
  );
};


const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

App.propTypes = {

};

export default memo(App);
