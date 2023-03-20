package com.avidea.avitrain;

import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.models.Photo;
import com.avidea.avitrain.models.Policy;
import com.avidea.avitrain.security.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

public class BaseController {

    public AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (AppUser) authentication.getPrincipal();
    }
}
