package es.iespuertodelacruz.jcr.tiktaktoe.game.domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import es.iespuertodelacruz.jcr.tiktaktoe.play.domain.Play;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;

public class Game {
    Long id;
    User player1;
    User player2;
    boolean finished;
    List<Play> plays;
    Date createdAt;
    User winner;

    public Game() {
    }

    public Game(Long id, User player1, User player2, boolean finished, List<Play> plays, Date createdAt) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.finished = finished;
        this.plays = plays;
        this.createdAt = createdAt;
    }

    public Game(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPlayer1() {
        return this.player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return this.player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public boolean getFinished() {
        return this.finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<Play> getPlays() {
        return this.plays;
    }

    public void setPlays(List<Play> plays) {
        this.plays = plays;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getWinner() {
        return this.winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public Game id(Long id) {
        setId(id);
        return this;
    }

    public Game player1(User player1) {
        setPlayer1(player1);
        return this;
    }

    public Game player2(User player2) {
        setPlayer2(player2);
        return this;
    }

    public Game finished(boolean finished) {
        setFinished(finished);
        return this;
    }

    public Game plays(List<Play> plays) {
        setPlays(plays);
        return this;
    }

    public Game createdAt(Date createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Game)) {
            return false;
        }
        Game game = (Game) o;
        return Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", player1='" + getPlayer1() + "'" +
                ", player2='" + getPlayer2() + "'" +
                ", finished='" + isFinished() + "'" +
                ", plays='" + getPlays() + "'" +
                "}";
    }

}
