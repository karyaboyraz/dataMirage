package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class CommerceProviderTest {
    private CommerceProvider commerceProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        commerceProvider = new CommerceProvider(new RandomService());
    }

    @RepeatedTest(20)
    void department_ShouldReturnValidDepartment() {
        String department = commerceProvider.department();
        assertNotNull(department);
        assertFalse(department.isEmpty());
    }

    @RepeatedTest(20)
    void productName_ShouldReturnValidName() {
        String productName = commerceProvider.productName();
        assertNotNull(productName);
        assertFalse(productName.isEmpty());
    }

    @RepeatedTest(20)
    void material_ShouldReturnValidMaterial() {
        String material = commerceProvider.material();
        assertNotNull(material);
        assertFalse(material.isEmpty());
    }

    @RepeatedTest(20)
    void promotionCode_ShouldReturnValidCode() {
        String code = commerceProvider.promotionCode();
        assertNotNull(code);
        assertFalse(code.isEmpty());
        assertTrue(code.matches("^PROMO-[A-Z]{4}-\\d{4}$"));
    }
} 