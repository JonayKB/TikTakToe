package es.iespuertodelacruz.jcr.tiktaktoe.user.infrastructure.adapters.secondary;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class UserDocument {
    @Field("id")
    private Long id;

    @Field("name")
    private String name;

    @Field("password")
    private String password;

    @Field("mail")
    @Indexed(unique = true)
    private String mail;

    @Field("rol")
    private String rol;

    @Field("verified")
    private boolean verified;

    @Field("verification_token")
    private String verificationToken;

    @Field("created_at")
    private Date createdAt;

    @Field("image_path")
    private String imagePath;

    public UserDocument() {
    }

    public UserDocument(Long id, String name, String password, String mail, String rol, boolean verified,
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDocument)) {
            return false;
        }
        UserDocument UserDocument = (UserDocument) o;
        return Objects.equals(id, UserDocument.id);
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
