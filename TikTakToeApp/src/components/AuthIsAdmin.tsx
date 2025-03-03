import { View, Text } from 'react-native'
import React, { ReactNode, useContext } from 'react'
import { AppContext } from './AppContextProvider'

type Props = {
    children: ReactNode
}

const AuthIsAdmin = (props: Props) => {
    const { userDetails } = useContext(AppContext);
    if (userDetails?.rol !== "ROLE_ADMIN") {
        return null;
    }
    return <>{props.children}</>;

}

export default AuthIsAdmin

