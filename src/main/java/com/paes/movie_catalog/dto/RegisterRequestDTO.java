package com.paes.movie_catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(

        @NotBlank(message = "O campo de usuário é obrigatório.")
        @Size(min = 5, message = "O usuário tem que ter no mínimo 5 caracteres")
        @Size(max = 20, message = "O usuário tem que ter no máximo 20 caracteres")
        String username,

        @NotBlank(message = "O campo de usuário é obrigatório.")
        @Size(max = 200, message = "O email tem que ter no máximo 200 caracteres")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        @Size(max = 100, message = "A senha deve ter no máximo 100 caracteres")
        String password
) {
}
