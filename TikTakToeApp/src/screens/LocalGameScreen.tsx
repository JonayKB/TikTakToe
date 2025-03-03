import { View, Text, TouchableOpacity, Image, Alert } from 'react-native'
import React, { useContext, useEffect, useRef, useState } from 'react'
import CellComponent from '../components/CellComponent'
import { AppContext } from '../components/AppContextProvider'
import styles from '../theme/Containers'
import stylesGame from '../theme/Game'
import formStyles from '../theme/Forms'
import { PrincipalStackProps } from '../navigations/PrincipalStackNavigation'
import { NativeStackScreenProps } from '@react-navigation/native-stack'
import { LocalGame } from '../data/entities/LocalGame'
import { LocalGameRepository } from '../data/Database'

type Props = NativeStackScreenProps<PrincipalStackProps, "LocalGame">;


const LocalGameScreen = ({ navigation, route }: Props) => {
    const localGame = useRef<LocalGame>(new LocalGame());
    const [cells, setCells] = useState<string[][]>(localGame.current?.board);


    function checkEnd() {
        const state = localGame.current.checkEnd()
        if (state) {
            if (state === "X") {
                Alert.alert("You win")
            } else if (state === "O") {
                Alert.alert("You lose")
            } else {
                Alert.alert("Draw")
            }

        }
    }
    function play(x: number, y: number) {
        if (localGame.current && !localGame.current.finished) {
            localGame.current.play(x, y, "X");
            checkEnd()
            playIA();
            setCells([...localGame.current.board]);
        }
    }
    function playIA() {
        if (localGame.current && !localGame.current.finished) {
            let x = Math.floor((Math.random() * 100) % 3);
            let y = Math.floor((Math.random() * 100) % 3);
            while (localGame.current.play(x, y, "O") === false) {
                x = Math.floor((Math.random() * 100) % 3);
                y = Math.floor((Math.random() * 100) % 3);

            }
            checkEnd()
            setCells([...localGame.current.board]);
        }
    }
    async function leaveNavigate() {

        LocalGameRepository.save(localGame.current);

        navigation.navigate("ModeSelector");
        navigation.reset({
            index: 0,
            routes: [{ name: "ModeSelector" }]
        })
    }
    useEffect(() => {
        if (route?.params?.id) {
            LocalGameRepository.findOne({
                where: {
                    id: route?.params?.id
                }
            }).then((game) => {
                if (game) {
                    localGame.current = game;
                    setCells([...game.board]);

                }

            })
        }
    }, [route?.params?.id])

    return (
        <View style={{ ...styles.container, justifyContent: "space-between" }}>
            <View style={stylesGame.players}>
                <View style={stylesGame.player}>
                    <Image source={{ uri: "https://cdn-icons-png.flaticon.com/512/10337/10337609.png" }} style={stylesGame.playerImage} />
                    <Text style={stylesGame.playerText}>You</Text>
                </View>
                <Text style={stylesGame.VSText}>VS</Text>
                <View style={stylesGame.player}>
                    <Image source={{ uri: "https://t4.ftcdn.net/jpg/09/43/48/93/360_F_943489384_zq3u5kkefFjPY3liE6t81KrX8W3lvxSz.jpg" }} style={stylesGame.playerImage} />
                    <Text style={stylesGame.playerText}>IA</Text>
                </View>
            </View>
            <View style={stylesGame.board}>
                {cells.map((row, y) => {
                    return (
                        <View key={y} style={{ flexDirection: "row" }}>
                            {row.map((cell, x) => {
                                return (
                                    <CellComponent key={x} cell={cell} x={x} y={y} play={play} touchable={!localGame.current?.finished} />
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

export default LocalGameScreen


