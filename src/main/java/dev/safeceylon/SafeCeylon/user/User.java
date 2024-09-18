package dev.safeceylon.SafeCeylon.user;

import java.time.LocalDateTime;

public record User(
    Integer id,
    String fullName,
    String nic,
    String mobile,
    String email,
    String address,
    String district,
    String gn_division,
    String password,
    LocalDateTime created_at
) {}
