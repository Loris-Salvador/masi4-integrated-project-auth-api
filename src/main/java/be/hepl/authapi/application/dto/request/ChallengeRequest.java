package be.hepl.authapi.application.dto.request;

/// <comments>
/// Objet reçu lors de la demande d'envoie du challenge (mail/sms) -> le choix du type est en fonction de l'endpoint
/// </comments>
public record ChallengeRequest (
  String email
){}
