import { View, Text, TouchableOpacity } from 'react-native'
import React, { useContext } from 'react'
import styles from '../theme/Containers'
import { PrincipalStackProps } from '../navigations/PrincipalStackNavigation';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import { AppContext } from '../components/AppContextProvider';
import useApi from '../hooks/useApi';
import formStyles from '../theme/Forms';

type Props = NativeStackScreenProps<PrincipalStackProps, "Multiplayer">;


const MultiPlayerScreen = ({ navigation, route }: Props) => {
    const { userDetails, setUserDetails, setLoading, setOnlineGame } = useContext(AppContext);
    const { joinGame, spectateGame } = useApi();
    async function joinGameNavigate() {
        setLoading(true);
        const game = await joinGame(userDetails);
        if (game) {
            setOnlineGame(game);
            navigation.navigate("OnlineGame", { spectate: false });
            navigation.reset({
                index: 0,
                routes: [{ name: "OnlineGame", params: { spectate: false } }]
            })
        }
        setLoading(false);
    }
    async function spectateGameNavigate() {
        setLoading(true);
        const game = await spectateGame(userDetails);
        if (game) {
            setOnlineGame(game);
            navigation.navigate("OnlineGame", { spectate: true });
            navigation.reset({
                index: 0,
                routes: [{ name: "OnlineGame", params: { spectate: true } }]
            })
        }
        setLoading(false);
    }
    return (
        <View style={{ ...styles.container, justifyContent: 'space-around' }}>
            <TouchableOpacity onPress={joinGameNavigate} style={{ ...formStyles.button, backgroundColor: '#2C3E50' }}>
                <Text style={formStyles.buttonText}>Join Game</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={spectateGameNavigate} style={{ ...formStyles.button, backgroundColor: '#7F8C8D' }}>
                <Text style={formStyles.buttonText}>Spectate Game</Text>
            </TouchableOpacity>
        </View>

    )
}

export default MultiPlayerScreen