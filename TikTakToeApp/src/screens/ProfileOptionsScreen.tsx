import { View, Text, Button, TextInput, TouchableOpacity, Image } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import { AppContext } from '../components/AppContextProvider'
import AsyncStorage from '@react-native-async-storage/async-storage'
import * as Keychain from 'react-native-keychain';
import useApi from '../hooks/useApi'
import { get } from 'http'
import formStyles from '../theme/Forms'
import { PrincipalStackProps } from '../navigations/PrincipalStackNavigation';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import styles from '../theme/Containers';

type Props = NativeStackScreenProps<PrincipalStackProps, "ProfileOptions">;

type User = {
    id: number,
    name: string,
    mail: string,
    password: string,
    imagePath: string,
}
const ProfileOptionsScreen = ({ navigation, route }: Props) => {
    const { setUserDetails, userDetails, setLoading } = useContext(AppContext);
    const [usuario, setUsuario] = useState<User>();
    const [image, setImage] = useState<string>();
    const { getSelf, getUserImage, getImage } = useApi();
    async function logout() {
        //AsyncStorage.setItem('userDetails', "");
        Keychain.resetGenericPassword();
        setUserDetails(undefined);
        navigation.navigate("ModeSelector");
        navigation.reset({
            index: 0,
            routes: [{ name: "ModeSelector" }]
        })

    }
    useEffect(() => {
        if (!userDetails) return;
        setLoading(true);
        async function getUser() {
            try {
                const user = await getSelf(userDetails);
                if (user?.imagePath) {
                    const image = await getImage(user?.imagePath);
                    setImage(image);

                }
                setUsuario(user);
                setLoading(false)
            } catch (error) {
                setLoading(false)

            }


        }
        getUser();
    }, [userDetails])

    return (
        <View style={{ ...styles.container, flex: 1, justifyContent: 'center', alignItems: 'center' }}>
            <Text style={{ ...formStyles.button, ...formStyles.buttonText }}>{usuario?.name}</Text>
            <Text style={{ ...formStyles.imageButton, ...formStyles.buttonText }}>{usuario?.mail}</Text>
            {usuario?.imagePath &&
                <View style={styles.imageContainer}>
                    <Image source={{ uri: image }} style={styles.image} />
                </View>
            }

            <TouchableOpacity onPress={logout} style={{ ...formStyles.button, backgroundColor: 'red' }}>
                <Text style={formStyles.buttonText}>Logout</Text>
            </TouchableOpacity>
        </View>
    )
}

export default ProfileOptionsScreen