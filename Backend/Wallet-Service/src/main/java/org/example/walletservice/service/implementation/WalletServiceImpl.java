package org.example.walletservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.example.walletservice.dto.Response.BalanceResponse;
import org.example.walletservice.dto.Response.WalletResponse;
import org.example.walletservice.entity.Wallet;
import org.example.walletservice.exception.WalletAlreadyExistsException;
import org.example.walletservice.exception.WalletNotFoundException;
import org.example.walletservice.repository.WalletRepository;
import org.example.walletservice.service.WalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public WalletResponse createWallet(Long userId) {

        if (walletRepository.existsByUserId(userId)) {
            throw new WalletAlreadyExistsException("Ya existe la wallet");
        }

        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setBalance(new BigDecimal(100));
        wallet.setCreatedAt(LocalDateTime.now());
        String alias = userId.toString() + "ALIAS";
        wallet.setAlias(alias);

        walletRepository.save(wallet);

        return new WalletResponse(wallet.getUserId(), wallet.getBalance());
    }

    @Override
    public BalanceResponse getBalance(Long userId) {

        Optional<Wallet> wallet = walletRepository.findByUserId(userId);

        if (wallet.isEmpty()) {
            throw new WalletNotFoundException("No existe la wallet");
        }

        return new BalanceResponse(wallet.get().getUserId(), wallet.get().getBalance());
    }
}
