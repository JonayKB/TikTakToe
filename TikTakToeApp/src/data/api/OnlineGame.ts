export interface OnlineGame {
    id: number;
    player1: string;
    player2: string;
    createdAt: string;
    finished: boolean;
    board: string[][];
    winner: string;
}