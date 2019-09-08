package br.com.softplan.security.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CriptografiaService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String criptografarBCrypt(String texto){
        return bCryptPasswordEncoder.encode(texto);
    }

}
