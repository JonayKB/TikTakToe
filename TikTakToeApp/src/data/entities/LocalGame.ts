import { BaseEntity, Column, CreateDateColumn, Entity, PrimaryColumn, PrimaryGeneratedColumn, Timestamp } from "typeorm";
@Entity()
export class LocalGame extends BaseEntity {
    @PrimaryGeneratedColumn()
    id: number;

    @CreateDateColumn()
    createdAt: Timestamp;

    @Column("boolean")
    finished: boolean;

    @Column("simple-json")
    board: string[][];

    constructor() {
        super();
        this.board = [[" ", " ", " "], [" ", " ", " "], [" ", " ", " "]];
        this.finished = false;
    }

    play(x: number, y: number, player: "X" | "O") {
        if (x < 0 || x > 2 || y < 0 || y > 2) return false;
        if (!this.finished) {
            if (this.board[y][x] === " ") {
                this.board[y][x] = player;
                return true;
            }
            return false;
        }
    }

    checkDraw() {
        for (let row of this.board) {
            for (let cell of row) {
                if (cell === " ") {
                    return false;
                }
            }
        }
        return true;
    }
    checkWinner() {
        for (let i = 0; i < 3; i++) {
            if (this.board[i][0] !== " " && this.board[i][0] === this.board[i][1] && this.board[i][0] === this.board[i][2]) {
                return this.board[i][0];
            }
            if (this.board[0][i] !== " " && this.board[0][i] === this.board[1][i] && this.board[0][i] === this.board[2][i]) {
                return this.board[0][i];
            }
        }
        if (this.board[0][0] !== " " && this.board[0][0] === this.board[1][1] && this.board[0][0] === this.board[2][2]) {
            return this.board[0][0];
        }
        if (this.board[0][2] !== " " && this.board[0][2] === this.board[1][1] && this.board[0][2] === this.board[2][0]) {
            return this.board[0][2];
        }
        return null;
    }
    checkEnd() {
        let winner = this.checkWinner();
        if (winner) {
            this.finished = true;
            return winner;
        }
        if (this.checkDraw()) {
            this.finished = true;
            return "draw";
        }
        return null;
    }

}