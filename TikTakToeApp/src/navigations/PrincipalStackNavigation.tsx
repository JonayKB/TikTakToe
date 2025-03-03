import { View, Text } from 'react-native'
import React, { useContext, useEffect } from 'react'
import { createNativeStackNavigator, NativeStackScreenProps } from '@react-navigation/native-stack'
import LoginScreen from '../screens/LoginScreen'
import RegisterScreen from '../screens/RegisterScreen'
import { AppContext } from '../components/AppContextProvider'
import Spinner from 'react-native-loading-spinner-overlay'
import ModeSelectorScreen from '../screens/ModeSelectorScreen'
import MultiPlayer from '../screens/MultiPlayerScreen'
import SinglePlayer from '../screens/SinglePlayerScreen'
import ProfileOptionsScreen from '../screens/ProfileOptionsScreen'
import OnlineGame from '../screens/OnlineGameScreen'
import LocalGameScreen from '../screens/LocalGameScreen'

type Props = {};

export type PrincipalStackProps = {
    Login: { email: string },
    Register: undefined,
    Multiplayer: undefined,
    Singleplayer: undefined,
    ModeSelector: undefined,
    ProfileOptions: undefined,
    OnlineGame: { spectate?: boolean },
    LocalGame: { id: number },
}

const PrincipalStackNavigation = (props: Props) => {
    const Stack = createNativeStackNavigator<PrincipalStackProps>();
    const { loading } = useContext(AppContext);

    return (
        <>
            <Spinner
                visible={loading}
                textContent={"Procesando..."}
                overlayColor="rgba(0, 0, 0, 0.75)"
                color="white"
                animation="fade"
                textStyle={{ color: "white", fontSize: 16 }}
            />
            <Stack.Navigator id={undefined} screenOptions={{
                headerShown: false,
                contentStyle: { backgroundColor: 'black' },
            }}>
                <Stack.Screen
                    name="ModeSelector"
                    component={ModeSelectorScreen}
                />
                <Stack.Screen name="Login" component={LoginScreen} />
                <Stack.Screen name="Register" component={RegisterScreen} />
                <Stack.Screen name="Multiplayer" component={MultiPlayer} />
                <Stack.Screen name="Singleplayer" component={SinglePlayer} />
                <Stack.Screen name="ProfileOptions" component={ProfileOptionsScreen} />
                <Stack.Screen name="OnlineGame" component={OnlineGame} />
                <Stack.Screen name="LocalGame" component={LocalGameScreen} />

            </Stack.Navigator>
        </>
    )
}

export default PrincipalStackNavigation