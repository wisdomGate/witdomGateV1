package com.sa.mailnotification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
class MailNotificationApplicationTests {
    @Autowired
    private Service service;

    @Test
    void contextLoads() {
    }
    @Test
    public void sendemail(){
        assertThat("true"== service.sendemail("bhbbereket5@gmail.com")).isTrue();
    }

}
