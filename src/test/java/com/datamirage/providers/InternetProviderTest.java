package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class InternetProviderTest {
    private InternetProvider internetProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        internetProvider = new InternetProvider(new RandomService());
    }

    @RepeatedTest(20)
    void email_ShouldReturnValidEmail() {
        String email = internetProvider.email();
        assertNotNull(email);
        assertFalse(email.isEmpty());
        assertTrue(email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"));
    }

    @RepeatedTest(20)
    void username_ShouldReturnValidUsername() {
        String username = internetProvider.username();
        assertNotNull(username);
        assertFalse(username.isEmpty());
        assertTrue(username.matches("^[a-z0-9.]+$"));
    }

    @RepeatedTest(20)
    void domainName_ShouldReturnValidDomain() {
        String domain = internetProvider.domainName();
        assertNotNull(domain);
        assertFalse(domain.isEmpty());
        assertTrue(domain.matches("^[\\w-]+\\.[a-zA-Z]{2,}$"));
    }

    @RepeatedTest(20)
    void url_ShouldReturnValidUrl() {
        String url = internetProvider.url();
        assertNotNull(url);
        assertFalse(url.isEmpty());
        assertTrue(url.matches("^https?://[\\w\\-]+(\\.[\\w\\-]+)+[/#?]?.*$"));
    }

    @RepeatedTest(20)
    void macAddress_ShouldReturnValidMac() {
        String mac = internetProvider.macAddress();
        assertNotNull(mac);
        assertFalse(mac.isEmpty());
        assertTrue(mac.matches("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$"));
    }

    @RepeatedTest(20)
    void password_ShouldReturnValidPassword() {
        String password = internetProvider.password();
        assertNotNull(password);
        assertFalse(password.isEmpty());
        assertTrue(password.length() >= 8);
    }
} 