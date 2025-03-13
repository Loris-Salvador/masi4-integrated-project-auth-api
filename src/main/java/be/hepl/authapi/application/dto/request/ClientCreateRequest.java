package be.hepl.authapi.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClientCreateRequest (
   @Email
   String email,

   @NotBlank
   String password,

   @NotBlank(message = "Le numéro de téléphone est obligatoire")
   String telephoneNumber,

   String name,

   String firstName,

   String gender,

   @JsonFormat(pattern = "yyyy-MM-dd")
   LocalDate dateOfBirth
){}
