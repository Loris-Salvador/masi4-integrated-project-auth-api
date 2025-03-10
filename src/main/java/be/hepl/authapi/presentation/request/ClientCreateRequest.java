package be.hepl.authapi.presentation.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientCreateRequest (
   @Email
   String email,

   @NotBlank
   @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
   String password,

   @NotBlank(message = "Le numéro de téléphone est obligatoire")
   @Pattern(regexp = "^\\+?[0-9]{1,3}[0-9]{9,12}$", message = "Numéro de téléphone invalide")
   String telephoneNumber,

   @NotBlank
   String name,

   @NotBlank
   String firstName,

   String gender
){}
