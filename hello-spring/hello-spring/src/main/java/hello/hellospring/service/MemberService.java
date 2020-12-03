package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;


public class MemberService {
	
	private final MemberRepository memberRepository;
	

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	
	/* ȸ������ */
	public Long join(Member member) {
		//���� �̸��� �ִ� �ߺ�ȸ���� ����
		validateDuplicateMember(member);//�ߺ�ȸ�� ���� 
		memberRepository.save(member);
		return member.getId();
	}


	public void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("�̹� �����ϴ� ȸ���Դϴ�.");
			});
	}
	
	/* ��üȸ����ȸ */
	
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
}
