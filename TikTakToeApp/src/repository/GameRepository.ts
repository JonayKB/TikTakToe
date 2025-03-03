import axios from "axios";
import { Globals } from "../utils/Globals";
import { UserDetails } from "../data/entities/UserDetails";

export class GameRepository {
    public static async joinGame(userdetails: UserDetails) {
        axios.defaults.headers.common['Authorization'] = `Bearer ${userdetails.token}`;
        try {
            const data = await axios.post(Globals.API_URL + "v2/games/");

            return data.data;
        } catch (error) {
            throw new Error(error.response.data);
        }
    }

    public static async spectateGame(userdetails: UserDetails) {
        axios.defaults.headers.common['Authorization'] = `Bearer ${userdetails.token}`;
        try {
            const data = await axios.post(Globals.API_URL + "v2/games/spectate") ?? null;

                return data.data;
        } catch (error) {
            throw new Error(error.response.data);
        }
    }

    public static async getGameById(userdetails: UserDetails, id: number) {
        axios.defaults.headers.common['Authorization'] = `Bearer ${userdetails.token}`;
        try {
            const data = await axios.get(Globals.API_URL + "v2/games/" + id);
            return data.data;
        } catch (error) {
            throw new Error(error.response.data);
        }
    }

    public static async leaveGame(userdetails: UserDetails) {
        axios.defaults.headers.common['Authorization'] = `Bearer ${userdetails.token}`;
        try {
            const data = await axios.delete(Globals.API_URL + "v2/games/");
            return data.data;
        } catch (error) {
            throw new Error(error.response.data);
        }
    }

    public static async playGame(userdetails: UserDetails, x: number, y: number) {
        axios.defaults.headers.common['Authorization'] = `Bearer ${userdetails.token}`;
        try {
            const data = await axios.post(Globals.API_URL + "v2/games/plays/", {
                x: x,
                y: y
            });
            return data.data;
        } catch (error) {
            throw new Error(error.response.data);
        }
    }
}