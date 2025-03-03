import { View, Text, TouchableOpacity, FlatList } from 'react-native'
import React, { useEffect, useState } from 'react'
import { PrincipalStackProps } from '../navigations/PrincipalStackNavigation';
import { NativeStackScreenProps } from '@react-navigation/native-stack';
import styles from '../theme/Containers';
import formStyles from '../theme/Forms';

import { LocalGame } from '../data/entities/LocalGame';
import { LocalGameRepository } from '../data/Database';

type Props = NativeStackScreenProps<PrincipalStackProps, "Singleplayer">;


const SinglePlayerScreen = ({ navigation, route }: Props) => {
  const [games, setGames] = useState<LocalGame[]>([]);
  const [reload, setReload] = useState<boolean>(false);
  useEffect(() => {
    LocalGameRepository.find({
      order: {
        createdAt: "DESC"
      }
    }).then((games) => {
      setGames(games);
    });
  }, [reload])

  function navigateToGame(id?: number) {
    navigation.navigate("LocalGame", { id });
    navigation.reset({
      index: 0,
      routes: [{ name: "LocalGame", params: { id } }]
    })
  }
  return (
    <View style={styles.container}>
      <TouchableOpacity style={formStyles.button} onPress={() => navigateToGame()}>
        <Text style={formStyles.buttonText}>New Game</Text>
      </TouchableOpacity>

      <FlatList
        data={games}
        renderItem={({ item }) => (
          <TouchableOpacity style={formStyles.button} onPress={() => navigateToGame(item.id)}>
            <Text style={formStyles.buttonText}>{item?.createdAt?.toLocaleString()} : {item?.finished ? "Finished" : "In Progress"}</Text>
          </TouchableOpacity>
        )}
        style={styles.flatListContent}
        keyExtractor={(item) => item.id.toString()}
        onRefresh={() => setReload(!reload)}
        refreshing={false}
      />
    </View>
  )
}

export default SinglePlayerScreen