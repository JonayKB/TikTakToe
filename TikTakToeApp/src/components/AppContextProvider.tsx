import { View, Text } from 'react-native'
import React, { createContext, Dispatch, SetStateAction, useEffect, useState } from 'react'

import axios from 'axios';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { UserDetails } from '../data/entities/UserDetails';
import * as Keychain from 'react-native-keychain';
import { OnlineGame } from '../data/api/OnlineGame';




export interface AppContextType {
    reload: boolean;
    setReload: Dispatch<SetStateAction<boolean>>;
    userDetails: UserDetails;
    setUserDetails: Dispatch<SetStateAction<UserDetails>>;
    loading: boolean;
    setLoading: Dispatch<SetStateAction<boolean>>;
    onlineGame: OnlineGame;
    setOnlineGame: Dispatch<SetStateAction<OnlineGame>>;
}

export const AppContext = createContext<AppContextType>({} as AppContextType);

export const AppContextProvider = (props: any) => {
    const [userDetails, setUserDetails] = useState<UserDetails>()
    const [reload, setReload] = useState<boolean>(false);
    const [loading, setLoading] = useState<boolean>(false);
    const [onlineGame, setOnlineGame] = useState<OnlineGame>();

    useEffect(() => {
            Keychain.getGenericPassword().then((credentials) => {
                if (credentials) {
                    setUserDetails(JSON.parse(credentials.password));
                }
            });
            /*AsyncStorage.getItem('userDetails').then((data) => {
                if (data) {
                    setUserDetails(JSON.parse(data));
                }
            });*/
        
    }, []);




    const contextValues: AppContextType = {
        reload,
        setReload,
        userDetails,
        setUserDetails,
        loading,
        setLoading,
        onlineGame,
        setOnlineGame

    };
    return (
        <AppContext.Provider value={contextValues}>
            {props.children}
        </AppContext.Provider>
    );
};

export default AppContextProvider