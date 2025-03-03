package es.iespuertodelacruz.jcr.tiktaktoe.play.domain;

import java.util.Date;
import java.util.Objects;

import es.iespuertodelacruz.jcr.tiktaktoe.game.domain.Game;
import es.iespuertodelacruz.jcr.tiktaktoe.user.domain.User;

public class Play {
    Integer x;
    Integer y;
    Game game;
    User user;
    Date playedAt;

    public Play() {
    }

    public Play(int x, int y, Game game, User user, Date playedAt) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.user = user;
        this.playedAt = playedAt;
    }

    public Integer getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Integer getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getPlayedAt() {
        return this.playedAt;
    }

    public void setPlayedAt(Date playedAt) {
        this.playedAt = playedAt;
    }

    public Play x(int x) {
        setX(x);
        return this;
    }

    public Play y(int y) {
        setY(y);
        return this;
    }

    public Play game(Game game) {
        setGame(game);
        return this;
    }

    public Play user(User user) {
        setUser(user);
        return this;
    }

    public Play playedAt(Date playedAt) {
        setPlayedAt(playedAt);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Play)) {
            return false;
        }
        Play play = (Play) o;
        return x == play.x && y == play.y && Objects.equals(game, play.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, game);
    }

    @Override
    public String toString() {
        return "{" +
                " x='" + getX() + "'" +
                ", y='" + getY() + "'" +
                ", game='" + getGame() + "'" +
                ", user='" + getUser() + "'" +
                ", playedAt='" + getPlayedAt() + "'" +
                "}";
    }


}