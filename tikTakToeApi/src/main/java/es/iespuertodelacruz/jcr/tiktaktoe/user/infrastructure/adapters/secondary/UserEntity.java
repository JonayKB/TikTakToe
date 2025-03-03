package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String name;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String mail;

    @Column(nullable = false, length = 45)
    private String rol;

    @Column(nullable = false)
    private boolean verified;

    @Column(name = "verification_token", length = 255)
    private String verificationToken;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "image_path", nullable = true)
    private String imagePath;

    public UserEntity() {
    }

    public UserEntity(Long id, String name, String password, String mail, String rol, boolean verified,
            String verificationToken, Date createdAt, String imagePath) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.rol = rol;
        this.verified = verified;
        this.verificationToken = verificationToken;
        this.createdAt = createdAt;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public boolean getVerified() {
        return this.verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getVerificationToken() {
        return this.verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public UserEntity id(Long id) {
        setId(id);
        return this;
    }

    public UserEntity name(String name) {
        setName(name);
        return this;
    }

    public UserEntity password(String password) {
        setPassword(password);
        return this;
    }

    public UserEntity mail(String mail) {
        setMail(mail);
        return this;
    }

    public UserEntity rol(String rol) {
        setRol(rol);
        return this;
    }

    public UserEntity verified(boolean verified) {
        setVerified(verified);
        return this;
    }

    public UserEntity verificationToken(String verificationToken) {
        setVerificationToken(verificationToken);
        return this;
    }

    public UserEntity createdAt(Date createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserEntity)) {
            return false;
        }
        UserEntity userEntity = (UserEntity) o;
        return Objects.equals(id, userEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", password='" + getPassword() + "'" +
                ", mail='" + getMail() + "'" +
                ", rol='" + getRol() + "'" +
                ", verified='" + isVerified() + "'" +
                ", verificationToken='" + getVerificationToken() + "'" +
                ", createdAt='" + getCreatedAt() + "'" +
                "}";
    }

}
