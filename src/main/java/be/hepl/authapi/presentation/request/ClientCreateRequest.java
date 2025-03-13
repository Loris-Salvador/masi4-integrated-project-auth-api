package be.hepl.authapi.presentation.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

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

   int dateOfBirth


){}
