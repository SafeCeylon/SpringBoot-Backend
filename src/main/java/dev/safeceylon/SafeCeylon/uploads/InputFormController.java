package dev.safeceylon.SafeCeylon.uploads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inputform")
public class InputFormController {

    @Autowired
    private InputFormService inputFormService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitForm(@RequestBody InputFormRequest request) {
        String response = inputFormService.processForm(request);
        return ResponseEntity.ok("Form submitted successfully!");
    }
}
