package es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary.GameEntity;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary.UserEntity;

@Entity
@Table(name = "Moves")
public class PlayEntity {

    @EmbeddedId
    private PlayEntityId id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity user;

    @Column(name = "played_at")
    private Date playedAt;

    public PlayEntity() {
        this.id = new PlayEntityId();
    }

    public PlayEntity(Integer x, Integer y, GameEntity game, UserEntity user, Date playedAt) {
        this.id.setX(x);
        this.id.setY(y);
        this.id.setGame(game);
        this.user = user;
        this.playedAt = playedAt;
    }

    public Integer getX() {
        return this.id.getX();
    }

    public void setX(Integer x) {
        this.id.setX(x);
    }

    public Integer getY() {
        return this.id.getY();
    }

    public void setY(Integer y) {
        this.id.setY(y);
    }

    public GameEntity getGame() {
        return this.id.getGame();
    }

    public void setGame(GameEntity game) {
        this.id.setGame(game);
    }

    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getPlayedAt() {
        return this.playedAt;
    }

    public void setPlayedAt(Date playedAt) {
        this.playedAt = playedAt;
    }

    public PlayEntity x(Integer x) {
        setX(x);
        return this;
    }

    public PlayEntity y(Integer y) {
        setY(y);
        return this;
    }

    public PlayEntity game(GameEntity game) {
        setGame(game);
        return this;
    }

    public PlayEntity user(UserEntity user) {
        setUser(user);
        return this;
    }

    public PlayEntity playedAt(Date playedAt) {
        setPlayedAt(playedAt);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PlayEntity)) {
            return false;
        }
        PlayEntity playEntity = (PlayEntity) o;
        return Objects.equals(id, playEntity.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
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