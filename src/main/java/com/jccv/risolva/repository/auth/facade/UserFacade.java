package com.jccv.risolva.repository.auth.facade;


import com.jccv.risolva.dto.auth.UserDto;
import com.jccv.risolva.exception.BadRequestException;
import com.jccv.risolva.exception.ResourceNotFoundException;
import com.jccv.risolva.model.auth.User;
import com.jccv.risolva.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    @Autowired
    private UserRepository userRepository;


    @Value("${admin.specialSecretKey}")
    private String SECRET_KEY;

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    public User save(User user) {


        return userRepository.save(user);
    }

    public User save(User user, UserDto request) {
        if(checkAuthorityContext() != "SUPERADMIN" && !request.getSecretKey().equals(SECRET_KEY ) ){
            throw new BadRequestException("No tienes los privilegios para esta operacion");
        }
        return userRepository.save(user);
    }



    private String checkAuthorityContext(){
        String authority = "";
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().toList().get(0).toString();
        }
        return authority;
    }
}
