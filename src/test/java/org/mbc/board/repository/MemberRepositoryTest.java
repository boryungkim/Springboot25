package org.mbc.board.repository;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mbc.board.domain.Member;
import org.mbc.board.domain.MemberRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2

public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired

    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .mid("member"+i)
                    .mpw(passwordEncoder.encode("1111"))
                    .email("email"+i+"@@mbc.org")
                    .build();

            member.addRole(MemberRole.USER);
            if (i>= 90){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }

    @Test
    public void testRead(){

        Optional<Member> result = memberRepository.getWithRoles("member100");

        Member member = result.orElseThrow();

        log.info("---------member100회원의 롤을 출력해라---------------");
        log.info(member);
        log.info(member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> {
            log.info(memberRole.name());
        });

    }
    @Commit
    @Test
    public void testUpdate(){
        String mid = "email1@@mbc.org";
        String mpw = passwordEncoder.encode("1111");

        memberRepository.updatePassword(mid, mpw);
    }

}
