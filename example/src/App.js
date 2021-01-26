import React, {memo, useEffect} from 'react';
import { View, StyleSheet } from 'react-native';
import PropTypes from 'prop-types';
import {Settings} from 'react-native-fbsdk';

const App = () => {

  useEffect(()=> {
    const init = async ()=> {
      /*const res = await Settings.setAutoInitEnabledAsync(false);
      console.warn(res);*/


      setTimeout(async ()=> {
        const res = await Settings.initializeAsync('pososi.pizdu.blyad', 'ok budu');
        console.warn(res);
      }, 3000);
    };

    init();
  },[]);

  return (
    <View style={styles.container} />
  );
};


const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'gray',
  },
});

App.propTypes = {

};

export default memo(App);
