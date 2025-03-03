import { View, Text, TextInput, Button, Alert, Touchable, TouchableOpacity, Image } from 'react-native'
import React, { useContext, useEffect, useState } from 'react'
import useApi from '../hooks/useApi';
import { AppContext } from '../components/AppContextProvider';
import { PrincipalStackProps } from '../navigations/PrincipalStackNavigation';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import formStyles from '../theme/Forms';
import { Asset, launchImageLibrary } from 'react-native-image-picker';
import styles from '../theme/Containers';


type Props = NativeStackScreenProps<PrincipalStackProps, 'Register'>;


const RegisterScreen = ({ navigation, route }: Props) => {
    const { register } = useApi();
    const [name, setName] = useState<string>();
    const [email, setEmail] = useState<string>();
    const [emailConfirmation, setEmailConfirmation] = useState<string>();
    const [password, setPassword] = useState<string>();
    const [passwordConfirmation, setPasswordConfirmation] = useState<string>();
    const [image, setImage] = useState<Asset>();
    const { setLoading } = useContext(AppContext);
    const selectImage = async () => {
        launchImageLibrary({ mediaType: "photo" }, (response) => {
            if (response.didCancel) {
                console.log("Selección cancelada");
            } else if (response.errorCode) {
                console.log("Error:", response.errorMessage);
            } else {
                const asset = response.assets[0];
                setImage(asset);

            }
        });
    };
    async function registerNavigate() {
        setLoading(true);
        if (email !== emailConfirmation) {
            Alert.alert("Error", "Emails do not match");
            setLoading(false);
            return;
        }
        if (password !== passwordConfirmation) {
            Alert.alert("Error", "Passwords do not match");
            setLoading(false);
            return;
        }
        const formData = new FormData();
        formData.append('file', {
            uri: image.uri,
            name: sanitizeFilename(email) + "." + image.fileName.split('.').pop(),
            type: image.type,
        });
        const data = await register(name, email, password, formData);
        if (data) {
            navigation.navigate("Login", { email });
        }
        setLoading(false);

    }
    function gotToLogin() {
        navigation.navigate("Login");
        navigation.reset({
            index: 0,
            routes: [{ name: 'Login' }],
        });
    }

    return (
        <View style={formStyles.container}>
            <TextInput placeholder="Name" onChangeText={setName} style={formStyles.input} />
            <TextInput placeholder="Email" onChangeText={setEmail} style={formStyles.input} />
            <TextInput placeholder="EmailConfirmation" onChangeText={setEmailConfirmation} style={formStyles.input} />
            <TextInput placeholder="Password" onChangeText={setPassword} secureTextEntry style={formStyles.input} />
            <TextInput placeholder="PasswordConfirmation" onChangeText={setPasswordConfirmation} secureTextEntry style={formStyles.input} />
            <TouchableOpacity
                style={formStyles.imageButton}
                onPress={selectImage}
            >
                <Text style={formStyles.buttonText}>Seleccionar foto de perfil</Text>
            </TouchableOpacity>
            {image && <View style={styles.imageContainer}>
                <Image source={{ uri: image.uri }} style={styles.image} />
            </View>
            }

            <TouchableOpacity onPress={registerNavigate} style={formStyles.button}>
                <Text style={formStyles.buttonText}>Register</Text>
            </TouchableOpacity>
            <Text onPress={gotToLogin} style={{ ...formStyles.buttonText, color: "grey" }}>Login</Text>

        </View>
    )
}

export default RegisterScreen

function sanitizeFilename(filename: string) {
    return filename.replace(/[^a-zA-Z0-9.\-_]/g, '_');
}


