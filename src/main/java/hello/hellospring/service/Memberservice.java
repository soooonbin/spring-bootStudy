package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class Memberservice {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // MemberRepository 가 회원서비스 코드를 DI 가능하게 변경
    public Memberservice(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDulicateMember(member);  // 중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDulicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw  new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    *    전체 회원 조회
    * */
    public List<Member> findMeembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
