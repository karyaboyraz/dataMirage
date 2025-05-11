package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class CryptoProviderTest {
    private CryptoProvider cryptoProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        cryptoProvider = new CryptoProvider(new RandomService());
    }

    @RepeatedTest(20)
    void coinName_ShouldReturnValidName() {
        String name = cryptoProvider.coinName();
        assertNotNull(name);
        assertFalse(name.isEmpty());
    }

    @RepeatedTest(20)
    void coinSymbol_ShouldReturnValidSymbol() {
        String symbol = cryptoProvider.coinSymbol();
        assertNotNull(symbol);
        assertFalse(symbol.isEmpty());
    }

    @RepeatedTest(20)
    void blockchain_ShouldReturnValidBlockchain() {
        String blockchain = cryptoProvider.blockchain();
        assertNotNull(blockchain);
        assertFalse(blockchain.isEmpty());
    }

    @RepeatedTest(20)
    void walletAddress_ShouldReturnValidAddress() {
        String address = cryptoProvider.walletAddress();
        assertNotNull(address);
        assertFalse(address.isEmpty());
        assertEquals(34, address.length());
    }

    @RepeatedTest(20)
    void transactionHash_ShouldReturnValidHash() {
        String hash = cryptoProvider.transactionHash();
        assertNotNull(hash);
        assertFalse(hash.isEmpty());
        assertEquals(64, hash.length());
    }
} 