package com.jianhui.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class JwtStore implements UserDetails {

    private Integer sid;
    private String username;
    private String password;
    private String storeName;
    private String phone;
    private String email;
    private Integer state;
    private Collection<? extends GrantedAuthority> authorities;

    // 写一个能直接使用user创建jwtUser的构造器
    public JwtStore(Store store) {
        sid = store.getSid();
        username = store.getUsername();
        password = store.getPassword();
        storeName = store.getStoreName();
        phone = store.getPhone();
        email = store.getEmail();
        state = store.getState();
        authorities = Collections.singleton(new SimpleGrantedAuthority(store.getRole()));
    }

    // 获取权限信息，目前博主只会拿来存角色。。
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账号是否未过期，默认是false，记得要改一下
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号是否未锁定，默认是false，记得也要改一下
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 账号凭证是否未过期，默认是false，记得还要改一下
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 这个有点抽象不会翻译，默认也是false，记得改一下
    @Override
    public boolean isEnabled() {
        return true;
    }
}
