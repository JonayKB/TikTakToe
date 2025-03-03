import axios from "axios";
import { useState, useEffect } from "react";
import { Alert } from "react-native";


import { UserDetails } from "../data/entities/UserDetails";
import { jwtDecode } from "jwt-decode";
import { AuthRepository } from "../repository/AuthRepository";

import { UsuarioRepository } from "../repository/UsuarioRepository";
import { Asset } from "react-native-image-picker";
import { GameRepository } from "../repository/GameRepository";



function useApi() {
    const login = async (correo: string, password: string) => {
        try {
            return await AuthRepository.login(correo, password);
        } catch (error) {
            Alert.alert("Error", error.message);
        }
    };

    const register = async (nombre: string, correo: string, password: string, image: FormData) => {
        try {

            return await AuthRepository.register(nombre, correo, password, image);
        } catch (error) {
            Alert.alert("Error", error.message);
        }
    }
    const getSelf = async (userdetails: UserDetails) => {
        try {
            return await UsuarioRepository.getSelf(userdetails);
        } catch (error) {
            Alert.alert("Error", error.message);

        }
    }

    const getUserImage = async (userdetails: UserDetails, name: String) => {
        try {
            const user = await UsuarioRepository.getUserByName(userdetails, name);
            return await UsuarioRepository.getImage(user.imagePath);
        } catch (error) {
            Alert.alert("Error", error.message);
        }
    }

    const getImage = async (imagePath: String) => {
        try {
            return await UsuarioRepository.getImage(imagePath);
        } catch (error) {
            Alert.alert("Error", error.message);
        }
    }

    const joinGame = async (userdetails: UserDetails) => {
        try {
            return await GameRepository.joinGame(userdetails);
        } catch (error) {
            Alert.alert("Error", error.message);
        }
    }

    const spectateGame = async (userdetails: UserDetails) => {
        try {
            return await GameRepository.spectateGame(userdetails);
        } catch (error) {
            Alert.alert("Error", error.message);
        }
    }

    const getGameById = async (userdetails: UserDetails, id: number) => {
        try {
            return await GameRepository.getGameById(userdetails, id);
        } catch (error) {
            Alert.alert("Error", error.message);
        }
    }

    const leaveGame = async (userdetails: UserDetails) => {
        try {
            return await GameRepository.leaveGame(userdetails);
        } catch (error) {
            Alert.alert("Error", error.message);
        }
    }

    const playGame = async (userdetails: UserDetails, x: number, y: number) => {
        try {
            return await GameRepository.playGame(userdetails, x, y);
        } catch (error) {
            Alert.alert("Error", error.message);
        }
    }


    return {
        login, register, getSelf, getUserImage, getImage, joinGame,
        spectateGame, getGameById, leaveGame, playGame
    };
}

export default useApi;
