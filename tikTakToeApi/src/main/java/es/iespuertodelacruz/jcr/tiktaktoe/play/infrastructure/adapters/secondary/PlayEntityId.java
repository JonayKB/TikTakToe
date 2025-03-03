package es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

import es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary.GameEntity;

@Embeddable
public class PlayEntityId implements Serializable {

    @Column(name = "posx")
    private Integer x;


    @Column(name = "posy")
    private Integer y;

    @JoinColumn(name = "game_id")
    @ManyToOne
    private GameEntity game;


    public PlayEntityId() {
    }

    public PlayEntityId(Integer x, Integer y, GameEntity game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    public Integer getX() {
        return this.x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return this.y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public GameEntity getGame() {
        return this.game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public PlayEntityId x(Integer x) {
        setX(x);
        return this;
    }

    public PlayEntityId y(Integer y) {
        setY(y);
        return this;
    }

    public PlayEntityId game(GameEntity game) {
        setGame(game);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PlayEntityId)) {
            return false;
        }
        PlayEntityId playEntityId = (PlayEntityId) o;
        return Objects.equals(x, playEntityId.x) && Objects.equals(y, playEntityId.y) && Objects.equals(game, playEntityId.game);
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
            "}";
    }
    

}
