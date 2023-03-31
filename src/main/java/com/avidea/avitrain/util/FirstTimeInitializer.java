package com.avidea.avitrain.util;

import com.avidea.avitrain.models.Claim;
import com.avidea.avitrain.models.Photo;
import com.avidea.avitrain.models.Policy;
import com.avidea.avitrain.security.AppUser;
import com.avidea.avitrain.security.UserService;
import com.avidea.avitrain.services.ClaimService;
import com.avidea.avitrain.services.PhotoService;
import com.avidea.avitrain.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.util.Date;

@Component
public class FirstTimeInitializer implements CommandLineRunner {

    private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);

    @Autowired
    private UserService userService;
    @Autowired
    private PolicyService policyService;
    @Autowired
    private ClaimService claimService;
    @Autowired
    private PhotoService photoService;

    @Override
    public void run(String... strings) throws Exception {

        if (userService.findAll().isEmpty()) {
            logger.info("No Users accounts found. Creating some users");

            AppUser user = new AppUser("lina@avidea.com", "password", "Lina");
            userService.save(user);

            Policy policy1	= new Policy("P1200", "ABC123", "lina",new Date(), new Date());
            Claim claim1= new Claim("AA110", Claim.Status.closed,
                    new Date(), new Date(),policy1);
            Claim claim2= new Claim("115600", Claim.Status.ongoing,
                    new Date(), new Date(),policy1);
            Claim claim3= new Claim("11601", Claim.Status.open,
                    new Date(), new Date(),policy1);
            Photo p1 = new Photo("titlePH","descriptionphoto","linkphoto1", claim1);
            Photo p2 = new Photo("titlePH","descriptionphoto","linkphoto2", claim1);
            Photo p3 = new Photo("titlePH","descriptionphoto","linkphoto3", claim2);
            Photo p4 = new Photo("titlePH","descriptionphoto","linkphoto4", claim2);
            Photo p5 = new Photo("titlePH","descriptionphoto","linkphoto5", claim3);
            Photo p6 = new Photo("titlePH","descriptionphoto","linkphoto6", claim3);

            policyService.addPolicy(policy1);
            claimService.addClaim(claim1);
            claimService.addClaim(claim2);
            claimService.addClaim(claim3);
            photoService.addPhoto(p1);
            photoService.addPhoto(p2);
        }
    }
}
