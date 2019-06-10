package org.starfish.identity.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.starfish.identity.dao.BaseDao;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"org.starfish.identity.admin","org.starfish.identity.service"})
@MapperScan(value = "org.starfish.identity.dao",markerInterface = BaseDao.class)
public class StarfishIdentityAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarfishIdentityAdminApplication.class, args);
    }

}
