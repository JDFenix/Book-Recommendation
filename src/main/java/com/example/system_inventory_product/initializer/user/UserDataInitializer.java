package com.example.system_inventory_product.initializer.user;

import com.example.system_inventory_product.entity.user.Role;
import com.example.system_inventory_product.entity.user.User;
import com.example.system_inventory_product.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataInitializer implements CommandLineRunner {

    @Autowired
    private final IUserRepository iUserRepository;



    @Autowired
    public UserDataInitializer(IUserRepository iuserRepository) {
        this.iUserRepository = iuserRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (iUserRepository.count() == 0) {

            User administrator = new User();
            administrator.setUsername("administratorJD");
            administrator.setEmail("administrator@gmail.com");
            administrator.setAvatar("https://api.dicebear.com/8.x/micah/svg?seed=administratorJD");
            administrator.setPassword("123456789");
            administrator.setRole(Role.administrator);
            iUserRepository.save(administrator);


            User reader = new User();
            reader.setUsername("readerOG");
            reader.setEmail("reader@gmail.com");
            reader.setAvatar("https://api.dicebear.com/8.x/micah/svg?seed=reaDer05");
            reader.setPassword("123456789");
            reader.setRole(Role.reader);
            iUserRepository.save(reader);
        }
    }
}
