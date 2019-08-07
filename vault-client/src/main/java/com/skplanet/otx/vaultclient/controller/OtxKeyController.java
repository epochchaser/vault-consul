package com.skplanet.otx.vaultclient.controller;

import com.skplanet.otx.vaultclient.domain.dto.OtxKey;
import com.skplanet.otx.vaultclient.service.KVSecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/key")
public class OtxKeyController {
    @Autowired
    private KVSecretService kvSecretService;

    @GetMapping("/master")
    public String getMasterKey(){
        return kvSecretService.getMasterKey();
    }

    @PostMapping("/master")
    public boolean writeMasterKey(@RequestBody OtxKey req){
        return kvSecretService.writeMasterKey(req);
    }
}

