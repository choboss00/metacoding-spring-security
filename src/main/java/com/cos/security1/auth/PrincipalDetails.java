package com.cos.security1.auth;

import com.cos.security1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킴
 * 로그인 진행이 완료되면 session 을 만들어줘야 함 ( Security ContextHolder )
 * 시큐리티가 가지고있는 session 에 저장해줘야 함
 * 오브젝트는 Authentication 타입 객체여야 함
 * Authentication 안에 User 정보가 있어야 함
 * User 오브젝트 타입은 UserDetails 타입 객체여야 함
 *
 * 즉 Security Session 안에 정보를 저장해줘야 하는데, 이때 저장하는 타입은 Authentication 타입의 객체여야 하고,
 * 이 객체 안에는 User 정보가 있어야 하는데, 이 User 정보 타입은 UserDetails 타입의 객체여야 한다.
 *
 * 그래서 PrinciplalDetails 라는 클래스를 만들어서 UserDetails 타입을 상속받으면 UserDetails 타입이 됨
 * Authentication 객체를 만들어서 이 객체를 넣어주면 됨
 * */

public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add((GrantedAuthority) () -> user.getRole());
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 우리 사이트에서 1년동안 회원이 로그인을 안하면 휴먼 계정으로 하기로 함
        // 현재시간 - 로그인시간 => 1년을 초과하면 return false;
        return true;
    }
}
