package dev.safeceylon.SafeCeylon.uploads;

import org.springframework.stereotype.Service;

@Service
public class InputFormService {

    public String processForm(InputFormRequest request) {
        // Process the form data
        System.out.println("Processing form data:");
        System.out.println("Date: " + request.getDate());
        System.out.println("Time: " + request.getTime());
        System.out.println("Description: " + request.getDescription());

        // You can add more logic here, like saving to a database
        return "Form processed successfully!";
    }
}
