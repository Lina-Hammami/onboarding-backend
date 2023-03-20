package com.avidea.avitrain.util;

import com.avidea.avitrain.security.AppUser;
import com.avidea.avitrain.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

@Component
public class FirstTimeInitializer implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);

    @Autowired
    private UserService userService;

    @Override
    public void run(String... strings) throws Exception {

        if (userService.findAll().isEmpty()) {
            logger.info("No Users accounts found. Creating some users");

            AppUser user = new AppUser("lina@avidea.com", "password", "Lina");
            userService.save(user);
        }
    }
}
