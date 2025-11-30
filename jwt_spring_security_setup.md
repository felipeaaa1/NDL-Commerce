# 🛡️ Implementando JWT e Controle de Roles no Spring Boot

## 📘 Passo a passo completo

### 1️⃣ Entidade `User` implementando `UserDetails`
A classe precisa implementar `UserDetails` e sobrescrever os métodos obrigatórios:

```java
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + type.name()));
}

@Override
public boolean isAccountNonExpired() { return true; }

@Override
public boolean isAccountNonLocked() { return true; }

@Override
public boolean isCredentialsNonExpired() { return true; }

@Override
public boolean isEnabled() { return true; }
```
> ⚠️ O Spring exige o prefixo `ROLE_` nas roles, ex: `ROLE_ADMIN`, `ROLE_USER`.

---

### 2️⃣ Repository implementando `UserDetailsService`
Crie uma classe que implementa `UserDetailsService` e use o repository para buscar o usuário:

```java
@Service
public class AuthorizationService implements UserDetailsService {

    private final JpaUserRepository repository;

    public AuthorizationService(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
```

---

### 3️⃣ Classe `SecurityConfiguration`

```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
```

---

### 4️⃣ Método `securityFilterChain`

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http, SecurityFilter securityFilter) throws Exception {
    return http
            .csrf(csrf -> csrf.disable()) // 🔒 CSRF = Cross Site Request Forgery, ataque que forja requisições autenticadas. Desativado pois JWT é stateless.
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // sem sessão
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .anyRequest().authenticated()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}
```

---

### 5️⃣ Configurar autenticação por Role

```java
@PreAuthorize("hasRole('ADMIN')")
```

ou múltiplas roles:

```java
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
```

> 🔹 Certifique-se de que `getAuthorities()` retorna `ROLE_` + nome da role.

---

### 6️⃣ Controller de Autenticação

```java
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data) {
        var userPass = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authManager.authenticate(userPass);
        var token = tokenService.generateToken((UserDataMapper) auth.getPrincipal());
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}
```

---

### 7️⃣ Beans auxiliares (`AuthenticationManager` e `PasswordEncoder`)

```java
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
}

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

---

### ✅ Fluxo completo

1. `User` implementa `UserDetails`  
2. `AuthorizationService` implementa `UserDetailsService`  
3. `SecurityConfiguration` define as regras e injeta o filtro JWT  
4. `AuthenticationController` gera o token via `TokenService`  
5. `SecurityFilter` intercepta requisições e autentica o usuário logado  
6. Endpoints usam `@PreAuthorize` conforme o role  

---

📄 **Resultado:** autenticação JWT 100% funcional e controle granular de acesso via roles.
