package com.paes.movie_catalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequestDTO(

        @NotBlank(message = "O campo de usuário é obrigatório")
        @Size(max = 50, message = "O número máximo é de 50 caracteres.")
        String username,

        @NotBlank(message = "A senha é obrigatória")
        @Size(max = 50 , min = 6, message = "O número máximo é de 50 caracteres.")
        @Size(min = 6, message = "O número mínimo é de 6 caracteres.")
        String password
) {
}
