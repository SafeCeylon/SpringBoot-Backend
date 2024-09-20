package dev.safeceylon.SafeCeylon.user;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Assuming PostgreSQL is using UUID
    private String id;

    private String name;
    private String nic;

    @Column(name = "mobile_number")
    private String mobileNumber;

    private String address;

    @Column(unique = true)
    private String email;

    @Column(name = "email_verified")
    private LocalDateTime emailVerified;

    private String image;

    @Column(columnDefinition = "TEXT")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private List<Account> accounts;

    @Column(name = "is_two_factor_enabled")
    private Boolean isTwoFactorEnabled = false;

    private String twoFactorConfirmation;

    // Getters and setters
    // Constructor(s)
    // Additional utility methods if needed

    // Default constructor
    public User() {}

    // Parameterized constructor
    public User(String id, String name, String nic, String mobileNumber, String address, String email, LocalDateTime emailVerified, String image, String password, UserRole role, boolean isTwoFactorEnabled, String twoFactorConfirmation) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.email = email;
        this.emailVerified = emailVerified;
        this.image = image;
        this.password = password;
        this.role = role;
        // this.accounts = accounts;
        this.isTwoFactorEnabled = isTwoFactorEnabled;
        this.twoFactorConfirmation = twoFactorConfirmation;
    }

    // Getters and setters (or use Lombok annotations if desired)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(LocalDateTime emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    // public List<Account> getAccounts() {
    //     return accounts;
    // }

    // public void setAccounts(List<Account> accounts) {
    //     this.accounts = accounts;
    // }

    public Boolean isTwoFactorEnabled() {
        return isTwoFactorEnabled;
    }

    public void setTwoFactorEnabled(Boolean isTwoFactorEnabled) {
        this.isTwoFactorEnabled = isTwoFactorEnabled;
    }

    public String getTwoFactorConfirmation() {
        return twoFactorConfirmation;
    }

    public void setTwoFactorConfirmation(String twoFactorConfirmation) {
        this.twoFactorConfirmation = twoFactorConfirmation;
    }
}
