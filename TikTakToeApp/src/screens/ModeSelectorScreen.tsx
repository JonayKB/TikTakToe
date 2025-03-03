import { View, Text, Touchable, TouchableOpacity } from 'react-native'
import React, { useContext } from 'react'
import styles from '../theme/Containers'
import formStyles from '../theme/Forms'
import { PrincipalStackProps } from '../navigations/PrincipalStackNavigation';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AppContext } from '../components/AppContextProvider';

type Props = NativeStackScreenProps<PrincipalStackProps, 'ModeSelector'>;


const ModeSelectorScreen = ({ navigation, route }: Props) => {
    const { userDetails, setUserDetails } = useContext(AppContext);
    return (
        <View style={{ ...formStyles.container, justifyContent: 'space-around' }}>
            <TouchableOpacity
                onPress={() => { navigation.navigate("Singleplayer") }}
                style={{ ...formStyles.button, backgroundColor: '#16A085' }}
            >
                <Text style={formStyles.buttonText}>Single Player</Text>
            </TouchableOpacity>
            <TouchableOpacity
                onPress={() => { navigation.navigate("Login") }}
                style={{ ...formStyles.button, backgroundColor: '#2980B9' }} 
            >
                <Text style={formStyles.buttonText}>Multiplayer</Text>
            </TouchableOpacity>
            {userDetails ?
                <TouchableOpacity
                    onPress={() => { navigation.navigate("ProfileOptions") }}
                    style={{ ...formStyles.button, backgroundColor: '#8E44AD' }} 
                >
                    <Text style={formStyles.buttonText}>Profile</Text>
                </TouchableOpacity>
                :
                <TouchableOpacity
                    onPress={() => { navigation.navigate("Login") }}
                    style={{ ...formStyles.button, backgroundColor: '#8E44AD' }}
                >
                    <Text style={formStyles.buttonText}>Login</Text>
                </TouchableOpacity>
            }
        </View>

    )
}

export default ModeSelectorScreen