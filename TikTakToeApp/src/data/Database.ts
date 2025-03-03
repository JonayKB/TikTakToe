import { DataSource } from 'typeorm';
import { LocalGame } from './entities/LocalGame';


export const datasource = new DataSource({
    database: 'tiktaktoe1.db',
    entities: [LocalGame],
    synchronize: true,
    location: 'default',
    type: 'react-native'
})

export const LocalGameRepository = datasource.getRepository(LocalGame);



