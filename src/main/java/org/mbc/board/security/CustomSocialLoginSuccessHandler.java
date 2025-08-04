package org.mbc.board.security;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mbc.board.security.dto.MemberSecurityDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor

public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final PasswordEncoder passwordEncoder;

    //            implements AuthenticationSuccessHandler 시큐리티에 내장된 인터페이스
    //카카오톡으로 로그인 성공시 해야되는 일
    //패스워드가 1111로 되어 있기 때문에 회원 수정 페이지로 이동
    //암호변경하게 셋팅


    @Override // AuthenticationSuccessHandler
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException{
        log.info("----------------------------");
        log.info("CustomLoginSuccessHandler onAuthenticationSuccess.........");
        log.info(authentication.getPrincipal());
        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
        String encodedPw = memberSecurityDTO.getMpw();

        if(memberSecurityDTO.isSocial()
        && (memberSecurityDTO.getMpw().equals("1111")
        || passwordEncoder.matches("1111", memberSecurityDTO.getMpw()))){
            log.info("Should Change Password");

            log.info("Redirect to Member Modify");
            response.sendRedirect("/member/modify");
            return;
        } else{
            response.sendRedirect("/board/list");
        }
    }

}
