package dev.safeceylon.SafeCeylon.user;

import dev.safeceylon.SafeCeylon.disastermanagement.Disaster;
import dev.safeceylon.SafeCeylon.disastermanagement.DisasterRepository;
import dev.safeceylon.SafeCeylon.shelterhospital.*;
import dev.safeceylon.SafeCeylon.DisasterVictim.*;
import dev.safeceylon.SafeCeylon.donations.*;
import dev.safeceylon.SafeCeylon.notification.*;
import dev.safeceylon.SafeCeylon.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.UUID;
import java.util.List;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //GET /api/users

    @GetMapping("")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        Optional<User> user = userRepository.findUserById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @Autowired 
    private DisasterRepository disasterRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @GetMapping("/map")
    public ResponseEntity<Map<String, List<?>>> getAllMapData() {
        System.out.println("Map request received");
        List<Disaster> disasters = disasterRepository.findDisastersNotReportedBy("USER");
        List<Shelter> shelters = shelterRepository.findAll();
        List<Hospital> hospitals = hospitalRepository.findAll();
        System.out.println("Disasters found: " + disasters.size());
        System.out.println("Shelters found: " + shelters.size());
        System.out.println("Hospitals found: " + hospitals.size());
        Map<String, List<?>> mapData = Map.of("disasters", disasters, "shelters", shelters, "hospitals", hospitals);
        return ResponseEntity.ok(mapData);
    }
    
    @Autowired
    private DisasterVictimRepository disasterVictimRepository;

    @GetMapping("/all-disasters")
    public List<Disaster> getAllDisastersUserNotPartOf(@RequestHeader("Authorization") String authorization){
        System.out.println("All disasters request received");
        String token = authorization.replace("Bearer ", "").trim(); // Extract the token part
        String userId = JwtUtils.getUserIdFromToken(token);
        Optional<User> userOptional = userRepository.findUserById(userId);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        List<Disaster> disasters = disasterVictimRepository.findDisastersUserNotPartOf(userId);
    
        return disasters;
    }

    @Autowired
    private NotificationRepository notificationRepository;
    
    @GetMapping("/get-notifications")
    public List<Notification> getNotifications(@RequestHeader("Authorization") String authorization) {
        System.out.println("Get notifications request received");
        String token = authorization.replace("Bearer ", "").trim(); // Extract the token part
        String userId = JwtUtils.getUserIdFromToken(token);
        Optional<User> userOptional = userRepository.findUserById(userId);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        return notificationRepository.findUnclearedNotificationsByUserId(userId);
    }

    

    //POST /api/users

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    void createUser(@RequestBody User user) {
        System.out.println("User registration request received: " + user.getEmail());
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
        userRepository.save(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        System.err.println("Login request received: " + request.getEmail() + " " + request.getPassword());
        // Find user by email
        Optional<User> userOptional = userRepository.findUserByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        User user = userOptional.get();

        // Compare provided hashed password with the stored hashed password
        if (!request.getPassword().equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        // Generate a response (e.g., JWT token or session info)
        String token = JwtUtils.generateToken(user.getId());
        return new LoginResponse(token, "Login successful");
    }

    @PostMapping("/userdata")
    public User getUserData(@RequestHeader("Authorization") String authorization) {
        System.out.println("Authorization header received: " + authorization);
        String token = authorization.replace("Bearer ", "").trim(); // Extract the token part
        String userId = JwtUtils.getUserIdFromToken(token);
        Optional<User> userOptional = userRepository.findUserById(userId);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        System.out.println("User data requested for: " + userOptional.get().getEmail());
        return userOptional.get();
    }

    @PostMapping("/add-victim")
    public ResponseEntity<Void> addVictim(@RequestHeader("Authorization") String authorization, @RequestBody Map<String, String> request) {
        System.out.println("Add victim request received: " + request.get("disasterId"));
        String token = authorization.replace("Bearer ", "").trim(); // Extract the token part
        String userId = JwtUtils.getUserIdFromToken(token);
        Optional<User> userOptional = userRepository.findUserById(userId);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        User user = userOptional.get();
        // Save the DisasterVictim entry
        DisasterVictim disasterVictim = new DisasterVictim(
            user.getId(),                      // idVictim
            request.get("disasterId"),         // idDisaster
            VictimStatus.ToReply               // victimStatus
        );
    
        disasterVictimRepository.save(disasterVictim);
    
        return ResponseEntity.ok().build();
    }

    @PostMapping("/clear-notification")
    public ResponseEntity<Void> clearNotification(@RequestHeader("Authorization") String authorization, @RequestBody Map<String, String> request) {
        System.out.println("Clear notification request received: " + request.get("notificationId"));
        String token = authorization.replace("Bearer ", "").trim(); // Extract the token part
        String userId = JwtUtils.getUserIdFromToken(token);
        Optional<User> userOptional = userRepository.findUserById(userId);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        String notificationId = request.get("notificationId");
        Optional<Notification> notificationOptional = notificationRepository.findById(notificationId);
        if (notificationOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification not found");
        }
        Notification notification = notificationOptional.get();
        notification.setCleared(true);
        notificationRepository.save(notification);
        return ResponseEntity.ok().build();
    }
    

    // For example, if you're using a session-based approach
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Logout request received");
        // Invalidate the session (if you're using sessions)
        request.getSession().invalidate();
        
        // You can also add a message indicating successful logout
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/password-reset")
    public ResponseEntity<String> sendPasswordResetEmail(@RequestBody Map<String, String> request) {
        System.out.println("Password reset request received: " + request.get("email"));
        String email = request.get("email");

        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not registered");
        }

        User user = userOptional.get();
        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        user.setTokenExpiry(Instant.now().plus(1, ChronoUnit.HOURS));
        userRepository.save(user);

        // Send the email (Use your EmailService here)
        String resetLink = "http://frontend-url/reset-password?token=" + resetToken;
        System.out.println("Reset link: " + resetLink); // For debugging

        // Use an email service in production
        return ResponseEntity.ok("Password reset link sent to your email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("password");

        Optional<User> userOptional = userRepository.findUserByResetToken(token);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid or expired token");
        }

        User user = userOptional.get();

        // Check if the token has expired
        if (user.getTokenExpiry().isBefore(Instant.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token has expired");
        }

        // Update the password
        user.setPassword(newPassword);
        user.setResetToken(null);
        user.setTokenExpiry(null);
        userRepository.save(user);

        return ResponseEntity.ok("Password reset successfully");
    }

    @PostMapping("/report-disaster")
    public ResponseEntity<String> reportDisaster(@RequestBody Map<String, String> request) {
        System.out.println("Disaster report request received: " + request.get("type"));

        Disaster disaster = new Disaster();
        disaster.setType(request.get("type"));
        disaster.setLatitude(Double.parseDouble(request.get("latitude")));
        disaster.setLongitude(Double.parseDouble(request.get("longitude")));
        disaster.setRadius( 3);
        disaster.setReportedAt(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        disaster.setResolved(false);
        disaster.setReportedBy("USER");
        disasterRepository.save(disaster);
        return ResponseEntity.ok("Disaster reported successfully");
    }

    @Autowired
    private MonetaryDonationRepository monetaryDonationsRepository;

    @Autowired
    private SupplyDonationRepository supplyDonationsRepository;

    @PostMapping("/add-mono-donation")
    public ResponseEntity<String> addMonoDonation(@RequestHeader("Authorization") String authorization, @RequestBody Map<String, String> request) {
        System.out.println("Mono donation request received: " + request.get("amount"));
        String token = authorization.replace("Bearer ", "").trim(); // Extract the token part
        String userId = JwtUtils.getUserIdFromToken(token);
        Optional<User> userOptional = userRepository.findUserById(userId);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
        MonetaryDonation donation = new MonetaryDonation(
            userOptional.get().getId(), // idDonor
            Double.parseDouble(request.get("amount")) // amount
        );
        monetaryDonationsRepository.save(donation);
        return ResponseEntity.ok("Donation added successfully");
    }

    @PostMapping("/add-sup-donation")
    public ResponseEntity<String> addSupDonation(
            @RequestHeader("Authorization") String authorization, 
            @RequestBody Map<String, Object> request) {
    
        System.out.println("Sup donation request received: " + request);
    
        String token = authorization.replace("Bearer ", "").trim();
        String userId = JwtUtils.getUserIdFromToken(token);
        Optional<User> userOptional = userRepository.findUserById(userId);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    
        String item = (String) request.get("supplies");
        if (item == null || item.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Supplies field is required");
        }
    
        String amountStr = (String) request.get("quantity");
        double quantity;
        try {
            quantity = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid quantity format");
        }
    
        String dateStr = (String) request.get("date");
        java.sql.Date date;
        try {
            date = java.sql.Date.valueOf(dateStr.split("T")[0]); // Convert ISO date to java.sql.Date
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format");
        }
    
        SupplyDonation donation = new SupplyDonation(
            userOptional.get().getId(),  // idDonor
            item,                       // supplies
            quantity,                   // quantity
            date                        // date
        );
        System.out.println("Donation: " + donation);
        supplyDonationsRepository.save(donation);
    
        return ResponseEntity.ok("Donation added successfully");
    }

    //PUT /api/users

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateUser(@RequestBody User user, @PathVariable String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        user.setId(id); // Ensure the ID is set for the entity being saved
        userRepository.save(user); // Save will update if the entity exists
    }
    
    //DELETE /api/users

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteUser(@PathVariable String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id); // deleteById is provided by JpaRepository
    }

    @GetMapping("/disaster")
    public List<User> getDisasterAdminsAndOfficers() {
        return userRepository.findByRoleIn(Arrays.asList(UserRole.DISASTER_ADMIN, UserRole.DISASTER_OFFICER));
    }
}
