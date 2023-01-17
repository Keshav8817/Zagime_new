package org.cyfwms.common.login.service;

import org.cyfwms.common.login.entity.BlacklistedTokens;
import org.cyfwms.common.login.repository.BlacklistedTokensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlacklistedTokensService {

    @Autowired
    private BlacklistedTokensRepository blacklistedTokensRepo;

    public void createBlacklistedTokens(BlacklistedTokens blacklistedTokens){
        blacklistedTokensRepo.save(blacklistedTokens);
    }

    public Optional<BlacklistedTokens> findByUsernameAndJwtToken(BlacklistedTokens blacklistedTokens){
        return blacklistedTokensRepo.findByUsernameAndJwtToken(//blacklistedTokens.getUsername()
                "",
                blacklistedTokens.getJwtToken());
    }
}
