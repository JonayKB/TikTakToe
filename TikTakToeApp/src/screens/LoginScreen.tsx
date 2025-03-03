import { View, Text, TextInput, Button, Touchable, TouchableOpacity } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import useApi from '../hooks/useApi'
import { AppContext } from '../components/AppContextProvider'
import { NativeStackScreenProps } from '@react-navigation/native-stack'
import AsyncStorage from '@react-native-async-storage/async-storage'
import { UserDetails } from '../data/entities/UserDetails'
import * as Keychain from 'react-native-keychain';
import formStyles from '../theme/Forms'
import { PrincipalStackProps } from '../navigations/PrincipalStackNavigation'

type Props = NativeStackScreenProps<PrincipalStackProps, 'Login'>;



const LoginScreen = ({ navigation, route }: Props) => {
    const { login } = useApi();
    const [email, setEmail] = useState<string>();
    const [password, setPassword] = useState<string>();
    const { userDetails, setUserDetails, setLoading } = useContext(AppContext);

    async function loginAndSave() {
        setLoading(true);

        const userdetailsGet: UserDetails = await login(email, password);
        if (userdetailsGet) {
            setUserDetails(userdetailsGet);
            //AsyncStorage.setItem('userDetails', JSON.stringify(userdetails));
            Keychain.setGenericPassword(email, JSON.stringify(userdetailsGet));
        } else {
            setPassword("");
        }

        setLoading(false);


    }
    function goToRegister() {
        navigation.navigate("Register");
        navigation.reset({
            index: 0,
            routes: [{ name: 'Register' }]
        })
    }
    useEffect(() => {
        if (userDetails) {
            navigation.navigate("Multiplayer");
            navigation.reset({
                index: 0,
                routes: [{ name: "ModeSelector" }, { name: 'Multiplayer' }]
            })
        }
    }, [userDetails])

    useEffect(() => {
        if (route.params?.email) {
            setEmail(route.params.email);
        }
    }, [route.params?.email])

    return (
        <View style={formStyles.container}>
            <TextInput placeholder="Email" onChangeText={setEmail} defaultValue={route.params?.email} style={formStyles.input} />
            <TextInput placeholder="Password" onChangeText={setPassword} secureTextEntry value={password} style={formStyles.input} />
            <TouchableOpacity onPress={loginAndSave} style={formStyles.button}>
                <Text style={formStyles.buttonText}>Login</Text>
            </TouchableOpacity>
            <Text onPress={goToRegister} style={{ ...formStyles.buttonText, color: "grey" }}>Register</Text>

        </View>
    )
}

export default LoginScreen