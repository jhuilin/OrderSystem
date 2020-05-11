package com.jianhui.service.auth;

import com.jianhui.model.County;
import com.jianhui.model.Store;
import com.jianhui.repository.CountyRepository;
import com.jianhui.repository.StoreRepository;
import com.jianhui.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @www.codesheep.cn
 * 20190312
 */
@Service
public class AuthServiceImpl{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("userService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    CountyRepository countyRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 登录
    public String login(Map<String, String> loginStore) {
        String username = loginStore.get("username");
        String password = loginStore.get("password");
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );

        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername( username );
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    // 注册
    public ResponseEntity<Store> registerStore(Map<String, Object> registerStore) {
        Store store = new Store();
        String storeName = (String)registerStore.get("storeName");
        String username = (String)registerStore.get("username");
        if (storeRepository.existsByStoreName(storeName) == true)
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        if (storeRepository.existsByUsername(username) == true )
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        store.setStoreName(storeName);
        store.setUsername(username);
        store.setEmail((String)registerStore.get("email"));
        store.setPhone((String)registerStore.get("phone"));
        store.setAddress1((String) registerStore.get("address1"));
        store.setCity((String) registerStore.get("city"));
        store.setZip((Integer) registerStore.get("zip"));
        store.setImageUrl((String) registerStore.get("imageUrl"));
        store.setState(1);
        store.setRole("ROLE_STORE");
        List<County> list = new LinkedList<>();
        List<LinkedHashMap<String,String>> temp = (List<LinkedHashMap<String, String>>) registerStore.get("counties");
        for (LinkedHashMap<String, String> t: temp){
            for (String name: t.values()){
                list.add(countyRepository.findByName(name));
            }
        }
        store.setCounties(list);
        store.setPassword(bCryptPasswordEncoder.encode((String)registerStore.get("password")));
        return new ResponseEntity<>(storeRepository.save(store),HttpStatus.OK);
    }

    public ResponseEntity<Store> registerAdmin(Map<String, Object> registerAdmin) {
        Store store = new Store();
        String username = (String)registerAdmin.get("username");
        if (storeRepository.existsByUsername(username) == true )
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        store.setUsername(username);
        store.setPassword(bCryptPasswordEncoder.encode((String)registerAdmin.get("password")));
        store.setRole("ROLE_ADMIN");
        return new ResponseEntity<>(storeRepository.save(store), HttpStatus.OK);
    }
}