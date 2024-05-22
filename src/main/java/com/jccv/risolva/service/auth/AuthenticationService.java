package com.jccv.risolva.service.auth;

import com.jccv.risolva.dto.auth.UserDto;
import com.jccv.risolva.dto.auth.UserUpdateDto;
import com.jccv.risolva.exception.BadRequestException;
import com.jccv.risolva.exception.ResourceNotFoundException;
import com.jccv.risolva.model.auth.Role;
import com.jccv.risolva.model.auth.Token;
import com.jccv.risolva.model.auth.User;
import com.jccv.risolva.repository.auth.TokenRepository;
import com.jccv.risolva.repository.auth.UserRepository;
import com.jccv.risolva.repository.auth.facade.UserFacade;
import com.jccv.risolva.security.jwt.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDto userDto;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private AuthenticationManager authenticationManager;




    public AuthenticationResponse register(UserDto request) {
        if (request.getUsername() == null) {
            throw new ResourceNotFoundException("El campo usuario no debe quedar vacio");
        }
        Optional<User> userFounded = repository.findByUsername(request.getUsername());

        if (userFounded.isPresent()) {
            throw new BadRequestException("El usuario ya existe");
        }

        User user =  buildUser(request, new User());
        user = userFacade.save(user, request);


        String token = jwtService.generateToken(user);
        saveToken(user, token);

        return new AuthenticationResponse(token);
    }


    private <T extends User> T buildUser(UserDto request, T user) {
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        return user;
    }


    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = repository.findByUsername(request.getUsername()).orElseThrow(() -> new ResourceNotFoundException("Usuario y/o Contraseña incorrecta"));
        String token = jwtService.generateToken(user);
        revokeAllTokensByUser(user);
        saveToken(user, token);
        return new AuthenticationResponse(token);
    }


    @Transactional
    public UserDto updateUser(Long userId, UserUpdateDto request) {

        User user = userFacade.findById(userId);

        verifyAuthorityToUpdate(user);

        if(request.getFirstName() != null){
            user.setFirstName(request.getFirstName());
        }
        if(request.getLastName() != null){
            user.setLastName(request.getLastName());
        }
        return userDto.convertUserToUserDto(userFacade.save(user));
    }



    private void verifyAuthorityToUpdate(User userToUpdate){
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            User user= repository.findByUsername( SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new BadRequestException("No se encontro el user de la cuenta"));

            // If you are not a SuperAdmin or Admin, only could modify you
            if(user.getUsername() !=userToUpdate.getUsername() && !user.getRole().equals(Role.ADMIN)) {
                if(!user.getRole().equals(Role.SUPERADMIN)){
                    throw new BadRequestException("Credenciales no validas para hacer el update");
                }
            }
            //If you no are SUPER ADMIN, Can't modify a SuperAdmin user
            if(Role.SUPERADMIN ==userToUpdate.getRole() && user.getRole() != Role.SUPERADMIN) {
                throw new BadRequestException("Credenciales no validas para hacer el update");
            }
        }
    }


    public boolean validateToken(Token token) {

        return jwtService.isValid(token.getToken());

    }

    private void revokeAllTokensByUser(User user) {
        List<Token> validTokenListByUser = tokenRepository.findAllTokensByUser(user.getId());
        if (!validTokenListByUser.isEmpty()) {
            validTokenListByUser.forEach(t -> t.setLoggedOut(true));
        }
        tokenRepository.saveAll(validTokenListByUser);
    }

    private void saveToken(User user, String token) {
        Token tokenToSave = Token.builder()
                .token(token)
                .loggedOut(false)
                .user(user)
                .build();
        tokenRepository.save(tokenToSave);

    }



}
