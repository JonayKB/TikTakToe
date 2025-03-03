package es.iespuertodelacruz.jcr.tiktaktoe.user.domain;

import java.util.Date;

import java.util.Objects;

public class User {
    private Long id;

    private String name;

    private String password;

    private String mail;

    private String rol;

    private boolean verified;

    private String verificationToken;

    private Date createdAt;

    private String imagePath;

    public User() {
    }

    public User(Long id, String name, String password, String mail, String rol, boolean verified,
            String verificationToken, Date createdAt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.rol = rol;
        this.verified = verified;
        this.verificationToken = verificationToken;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public User id(Long id) {
        setId(id);
        return this;
    }

    public User name(String name) {
        setName(name);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User mail(String mail) {
        setMail(mail);
        return this;
    }

    public User rol(String rol) {
        setRol(rol);
        return this;
    }

    public User verified(boolean verified) {
        setVerified(verified);
        return this;
    }

    public User verificationToken(String verificationToken) {
        setVerificationToken(verificationToken);
        return this;
    }

    public User createdAt(Date createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
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
