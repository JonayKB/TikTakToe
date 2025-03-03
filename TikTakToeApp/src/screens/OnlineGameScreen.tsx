import { View, Text, TouchableOpacity, Image, Alert } from 'react-native'
import React, { useContext, useEffect, useRef, useState } from 'react'
import styles from '../theme/Containers.ts'
import { PrincipalStackProps } from '../navigations/PrincipalStackNavigation.tsx';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import formStyles from '../theme/Forms.ts';
import { AppContext } from '../components/AppContextProvider.tsx';
import CellComponent from '../components/CellComponent.tsx';
import stylesGame from '../theme/Game.ts';
import useApi from '../hooks/useApi.ts';
import { OnlineGame } from '../data/api/OnlineGame.ts';

type Props = NativeStackScreenProps<PrincipalStackProps, "OnlineGame">;


const OnlineGameScreen = ({ navigation, route }: Props) => {
    const { onlineGame, userDetails, setOnlineGame } = useContext(AppContext);
    const { getUserImage, getGameById, leaveGame, playGame } = useApi();
    const [player1Image, setPlayer1Image] = useState<string>();
    const [player2Image, setPlayer2Image] = useState<string>();
    const intervalo = useRef<NodeJS.Timeout>();

    async function leaveNavigate() {
        if (!route.params?.spectate && !onlineGame?.finished) {
            await leaveGame(userDetails);
        }
        setOnlineGame(undefined);
        navigation.navigate("ModeSelector");
        navigation.reset({
            index: 0,
            routes: [{ name: "ModeSelector" }]
        })
    }
    function checkEnd(game: OnlineGame) {
        if (game?.winner) {
            Alert.alert("Winner", game.winner);
            clearInterval(intervalo.current);
            return;
        }
        if (game?.finished) {
            Alert.alert("Game finish", "Your game has ended in a draw");
            clearInterval(intervalo.current);
            return;
        }
    }
    async function play(x: number, y: number) {
        const game = await playGame(userDetails, x, y);
        if (game) {
            setOnlineGame(game);
            checkEnd(game);
        }
    }
    useEffect(() => {
        if (onlineGame?.player1) {
            getUserImage(userDetails, onlineGame?.player1).then((image) => {
                setPlayer1Image(image);
            });
        } else {
            setPlayer1Image("https://static.vecteezy.com/system/resources/previews/007/126/739/non_2x/question-mark-icon-free-vector.jpg");
        }
        if (onlineGame?.player2) {
            getUserImage(userDetails, onlineGame?.player2).then((image) => {
                setPlayer2Image(image);
            });
        } else {
            setPlayer2Image("https://static.vecteezy.com/system/resources/previews/007/126/739/non_2x/question-mark-icon-free-vector.jpg");
        }
    }, [onlineGame?.player1, onlineGame?.player2])
    useEffect(() => {
        intervalo.current = setInterval(() => {
            getGameById(userDetails, onlineGame?.id).then((lastGame) => {
                if (lastGame) {
                    setOnlineGame(lastGame);
                    checkEnd(lastGame);
                }
            });
        }, 500);

        return () => {
            clearInterval(intervalo.current)
            setOnlineGame(undefined);
        };
    }, []);

    return (
        <View style={{ ...styles.container, justifyContent: "space-between" }}>
            <View style={stylesGame.players}>
                <View style={stylesGame.player}>
                    <Image source={{ uri: player1Image }} style={stylesGame.playerImage} />
                    <Text style={stylesGame.playerText}>{onlineGame?.player1 ?? "???"}</Text>
                </View>
                <Text style={stylesGame.VSText}>VS</Text>
                <View style={stylesGame.player}>
                    <Image source={{ uri: player2Image }} style={stylesGame.playerImage} />
                    <Text style={stylesGame.playerText}>{onlineGame?.player2 ?? "???"}</Text>
                </View>
            </View>
            <View style={stylesGame.board}>
                {onlineGame?.board.map((row, y) => {
                    return (
                        <View key={y} style={{ flexDirection: "row" }}>
                            {row.map((cell, x) => {
                                return (
                                    <CellComponent key={x} cell={cell} x={x} y={y} play={play} touchable={!route.params?.spectate && !onlineGame?.finished} />
                                )
                            })}
                        </View>
                    )
                })}
            </View>
            <TouchableOpacity style={{ ...formStyles.leaveButton }} onPress={leaveNavigate}>
                <Text style={formStyles.buttonText}>Leave</Text>
            </TouchableOpacity>
        </View>
    )
}

export default OnlineGameScreen