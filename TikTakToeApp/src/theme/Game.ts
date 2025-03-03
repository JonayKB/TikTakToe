import { StyleSheet } from 'react-native';

const stylesGame = StyleSheet.create({
    cell: {
        width: 100,
        height: 100,
        borderWidth: 1,
        borderColor: '#000',
        justifyContent: 'center',
        alignItems: 'center',
    },
    cellText: {
        fontSize: 70,
        fontWeight: 'bold',
    },
    board: {
        justifyContent: "center",
        alignItems: "center",
        margin: 20,
        flex:2,
    },
    players: {
        flexDirection: "row",
        justifyContent: "space-around",
        margin: 20,
        flex:1
    },
    playerImage: {
        width: 100,
        height: 100,
        borderRadius: 100,
        backgroundColor: "black"
    },
    playerText:
    {
        fontSize: 20,
        fontWeight: "bold",
        justifyContent: "center",
        alignItems: "center",
        textAlign: "center",
    },
    VSText:{
        fontSize: 20,
        fontWeight: "bold",
        justifyContent: "center",
        alignItems: "center",
        textAlign: "center",
        textAlignVertical:"center",
        flex:1
    },
    player:{
        justifyContent: "center",
        alignItems: "center",
        flex:1,
    }
});

export default stylesGame;