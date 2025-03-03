import axios from "axios";
import { jwtDecode } from "jwt-decode";
import { Globals } from "../utils/Globals";
import { UserDetails } from "../data/entities/UserDetails";
interface CustomJwtPayload {
    sub: string;
    role: string;
}
export class AuthRepository {
    public static async login(correo: string, password: string) {
        try {
            const data = await axios.post(Globals.API_URL + "login", {
                mail: correo,
                password: password
            });
            const token = data.data;
            const decoded = jwtDecode<CustomJwtPayload>(token);
            return {
                token,
                name: decoded?.sub,
                rol: decoded?.role
            } as UserDetails;
        } catch (error) {
            throw new Error(error.response?.data);
        }
    };
    public static async register(name: string, mail: string, password: string, image: FormData) {
        try {
            const imagePost = await axios.post(Globals.API_URL + "images/", image, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            const data = await axios.post(Globals.API_URL + "register", {
                name: name,
                mail: mail,
                password: password,
                imagePath: imagePost.data
            });
            return data.data;

        } catch (error) {
            throw new Error(error.response?.data);
        }
    }
}
