package com.jianhui.service.auth;

import com.jianhui.model.JwtStore;
import com.jianhui.model.Store;
import com.jianhui.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Store store = storeRepository.findByUsername(s);
        if (store == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        JwtStore jwtStore = new JwtStore(store);
        return jwtStore;
    }

}