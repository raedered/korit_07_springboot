package com.example.cardatabase;

import com.example.cardatabase.domain.AppUser;
import com.example.cardatabase.domain.AppUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class AppRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    @DisplayName("findByUsername 메서드 검증")
    void findByUsername() {
        AppUser appUser = new AppUser("user1", "user1", "USER");
        appUserRepository.save(appUser);

        Optional<AppUser> users = appUserRepository.findByUsername("user1");

        assertThat(users).isPresent();
        assertThat(users.get().getRole()).isEqualTo("USER");

    }
}
