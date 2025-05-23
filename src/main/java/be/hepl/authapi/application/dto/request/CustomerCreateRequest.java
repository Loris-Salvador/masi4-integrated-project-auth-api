package be.hepl.authapi.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;


public record CustomerCreateRequest(
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

      String name,

      String firstName,

      String gender,

      @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
      Instant birthday
){}
