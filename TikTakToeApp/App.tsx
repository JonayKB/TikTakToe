import 'react-native-gesture-handler';
import React, { useEffect } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { SafeAreaView } from 'react-native-safe-area-context';
import LoginDrawerStackNavigation from './src/navigations/PrincipalStackNavigation';
import AppContextProvider from './src/components/AppContextProvider';
import { datasource } from './src/data/Database';




function App(): React.JSX.Element {
  useEffect(() => {
    async function initialiteDB() {
      try {
        datasource.initialize().then(() => {
          console.log('Database initialized');
        });
      } catch (error) {
        console.log(error);

      }
    }
    initialiteDB();
  }, []);
  return (
    <NavigationContainer>

      <SafeAreaView style={{ flex: 1 }}>
        <AppContextProvider>
          <LoginDrawerStackNavigation />
        </AppContextProvider>
      </SafeAreaView>
    </NavigationContainer>
  );
}


export default App;
