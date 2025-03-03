import { StyleSheet } from 'react-native';

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#F5F5F5',
        padding: 10,
    },
    flatListContent: {
        padding: 20,
        marginTop: 10,
        borderColor: '#000000',
        borderWidth: 1,
        borderRadius: 10,
    },
    reloadButton: {
        backgroundColor: '#007AFF',
        padding: 15,
        borderRadius: 10,
        margin: 10,
        alignItems: 'center',
    },
    reloadButtonText: {
        color: '#FFFFFF',
        fontSize: 16,
        fontWeight: 'bold',
    },
    image: {
        width: 100,
        height: 100,
        borderRadius: 100,
        borderWidth: 1,
        borderColor: 'black',

    },
    imageContainer: {
        justifyContent: 'center',
        alignItems: 'center',
        marginBottom: 10,
    },
});

export default styles;