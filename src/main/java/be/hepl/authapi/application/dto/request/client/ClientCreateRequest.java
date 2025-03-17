package be.hepl.authapi.application.dto.request.client;

import be.hepl.authapi.domain.model.client.ClientLoginMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record ClientCreateRequest (
   @Email(message = "Email is not formatted correctly")
   String email,

   @NotBlank(message = "The password can't be null or empty")
   @Pattern(
           regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[.;#!?])[A-Za-z\\d.;#!?]{8,}$",
           message = "The password must contain at least one uppercase letter, one digit, and one special character (., ;, #, !, ?), and be at least 8 characters long."
   )
   String password,

   @NotBlank(message = "The phone number can't be null or empty")
   String phoneNumber,

   String lastName,

   String firstName,

   String gender,

   @JsonFormat(pattern = "yyyy-MM-dd")
   LocalDate birthDate,

   String creditCard,

   String nationalId,

   Set<ClientLoginMethod> loginPreferences
){}
