import axios from "axios";
import { UserDetails } from "../data/entities/UserDetails";
import { Globals } from "../utils/Globals";

export class UsuarioRepository {
    public static async getSelf(userdetails: UserDetails) {
        axios.defaults.headers.common['Authorization'] = `Bearer ${userdetails.token}`;
        try {
            const data = await axios.get(Globals.API_URL + "v2/users/");

            return data.data;
        } catch (error) {
            throw new Error(error.response.data);
        }
    }
    public static async getUserByName(userdetails: UserDetails, name: String) {
        axios.defaults.headers.common['Authorization'] = `Bearer ${userdetails.token}`;
        try {
            const data = await axios.get(Globals.API_URL + "v2/users/" + name);
            return data.data;
        } catch (error) {
            throw new Error(error.response.data);
        }
    }
    public static async getImage(imageName: String) {
        try {
            return Globals.API_URL + "images/" + imageName;
        } catch (error) {
            throw new Error(error.response.data);
        }
    }
}