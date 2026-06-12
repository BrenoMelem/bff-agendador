package com.melem.bffagendadortarefas.infrastructure.Security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
/// SecurityScheme: Anotação do Swagger/OpenAPI que define como a autenticação funciona na documentação da API.
/// name = SecurityConfig.SECURITY_SHEME: Define o nome do esquema como "bearerAuth" (referenciado pela constante).
/// type = SecuritySchemeType.HTTP: Especifica que é autenticação HTTP.
/// bearerFormat = "JWT": Indica que o token é no formato JWT.
/// scheme = "bearer": Define que usa o esquema Bearer (padrão para JWT).
/// public class SecurityConfig: Classe que encapsula essa configuração.
/// public static final String SECURITY_SHEME: Constante reutilizável em outras anotações para referenciar este esquema de segurança.
@SecurityScheme(name = SecurityConfig.SECURITY_SHEME, type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT", scheme = "bearer")
public class SecurityConfig {
    public static final String SECURITY_SHEME = "bearerAuth"; // ELE TEM QUE SER UM ATRIBUTO ESTATICO
}
