import { View, Text, TouchableOpacity } from 'react-native'
import React from 'react'
import stylesGame from '../theme/Game'

type Props = {
    cell: string,
    x: number,
    y: number,
    play: (x: number, y: number) => void,
    touchable: boolean
}

const CellComponent = (props: Props) => {
    return (
        <TouchableOpacity key={props?.x} onPress={() => props?.play(props?.x, props?.y)} style={stylesGame.cell} disabled={!props.touchable}>
            <Text style={stylesGame.cellText}>{props?.cell}</Text>
        </TouchableOpacity>
    )
}

export default CellComponent