package com.skplanet.otx.vaultclient.service;

import com.skplanet.otx.vaultclient.config.VaultConfig;
import com.skplanet.otx.vaultclient.domain.dto.OtxKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.VaultResponseSupport;

@Slf4j
@Service
public class KVSecretService {
    @Autowired
    private Environment environment;

    private VaultOperations operations = new VaultConfig().vaultTemplate();;

    public String getMasterKey() {
        String otxSecretName = environment.getProperty("vault.otx-secret.name");
        String otxSecretPath = environment.getProperty("vault.otx-secret.path");

        VaultKeyValueOperations versioned = operations.opsForKeyValue(otxSecretName,
                VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);

        try {
            VaultResponseSupport<OtxKey> response = versioned
                    .get(otxSecretPath, OtxKey.class);

            if(null == response) throw new IllegalArgumentException("No key exists");

            return response.getData().getKey();
        }catch(Exception e){
            throw e;
        }
    }

    public boolean writeMasterKey(OtxKey req){
        String otxSecretName = environment.getProperty("vault.otx-secret.name");
        String otxSecretPath = environment.getProperty("vault.otx-secret.path");

        try {
            VaultKeyValueOperations versioned = operations.opsForKeyValue(otxSecretName,
                    VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);

            versioned.put(otxSecretPath, req);
        }catch(Exception e){
            log.error(e.getMessage());
            return false;
        }

        return true;
    }
}

