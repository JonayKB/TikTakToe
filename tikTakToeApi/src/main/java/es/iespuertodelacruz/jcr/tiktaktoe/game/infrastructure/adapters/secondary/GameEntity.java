package es.iespuertodelacruz.jcr.tiktaktoe.game.infrastructure.adapters.secondary;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Objects;

import es.iespuertodelacruz.jcr.tiktaktoe.play.infrastructure.adapters.secondary.PlayEntity;
import es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary.UserEntity;

@Entity
@Table(name = "games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    UserEntity player1;

    @ManyToOne
    @JoinColumn(nullable = true)
    UserEntity player2;

    @Column(name = "finished")
    Boolean finished;

    @Column(name = "created_at")
    Date createdAt;

    @OneToMany(mappedBy = "id.game")
    List<PlayEntity> plays;

    @ManyToOne
    @JoinColumn(nullable = true)
    UserEntity winner;

    public GameEntity() {
    }

    public GameEntity(Long id) {
        this.id = id;
    }

    public GameEntity(Long id, UserEntity player1, UserEntity player2, Boolean finished, Date createdAt,
            List<PlayEntity> plays) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.finished = finished;
        this.createdAt = createdAt;
        this.plays = plays;
    }

    public GameEntity(Long id, UserEntity player1, UserEntity player2, Boolean finished, Date createdAt) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.finished = finished;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getPlayer1() {
        return this.player1;
    }

    public void setPlayer1(UserEntity player1) {
        this.player1 = player1;
    }

    public UserEntity getPlayer2() {
        return this.player2;
    }

    public void setPlayer2(UserEntity player2) {
        this.player2 = player2;
    }

    public Boolean isFinished() {
        return this.finished;
    }

    public Boolean getFinished() {
        return this.finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<PlayEntity> getPlays() {
        return this.plays;
    }

    public void setPlays(List<PlayEntity> plays) {
        this.plays = plays;
    }

    public UserEntity getWinner() {
        return this.winner;
    }

    public void setWinner(UserEntity winner) {
        this.winner = winner;
    }

    public GameEntity id(Long id) {
        setId(id);
        return this;
    }

    public GameEntity player1(UserEntity player1) {
        setPlayer1(player1);
        return this;
    }

    public GameEntity player2(UserEntity player2) {
        setPlayer2(player2);
        return this;
    }

    public GameEntity finished(Boolean finished) {
        setFinished(finished);
        return this;
    }

    public GameEntity createdAt(Date createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public GameEntity plays(List<PlayEntity> plays) {
        setPlays(plays);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GameEntity)) {
            return false;
        }
        GameEntity gameEntity = (GameEntity) o;
        return Objects.equals(id, gameEntity.id);
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
                ", createdAt='" + getCreatedAt() + "'" +
                ", plays='" + getPlays() + "'" +
                "}";
    }

}
