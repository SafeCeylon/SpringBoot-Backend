package dev.safeceylon.SafeCeylon.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Assuming PostgreSQL is using UUID
    private String id;
    private String name;
    private String nic;
    private String address;
    private String twoFactorConfirmation;
    private String image;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(unique = true)
    private String email;

    @Column(name = "email_verified")
    private LocalDateTime emailVerified;

    @Column(columnDefinition = "TEXT")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private List<Account> accounts;

    @Column(name = "is_two_factor_enabled")
    private Boolean isTwoFactorEnabled = false;



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

    // public List<Account> getAccounts() {
    //     return accounts;
    // }

    // public void setAccounts(List<Account> accounts) {
    //     this.accounts = accounts;
    // }

    public Boolean isTwoFactorEnabled() {
        return isTwoFactorEnabled;
    }

}
