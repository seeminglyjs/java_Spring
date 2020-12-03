package hello.hellospring.service;

import hello.hellospring.domain.Member; 
import hello.hellospring.repository.MemoryMemberRepository; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;



import static org.assertj.core.api.Assertions.*; 
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

class MemberServiceTest {
	
	MemberService memberService ;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	
	@Test
	void testJoin() {
		//given
		Member member = new Member();
		member.setName("hello");
			
		//when
		Long saveId = memberService.join(member);
		
		//then
		Member findMember = memberService.findOne(saveId).get();
		 assertThat(member.getName()).isEqualTo(findMember.getName());
		
	}
	
	@Test
	void �ߺ�ȸ������() {
		//given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		//when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("�̹� �����ϴ� ȸ���Դϴ�.");
		
		/*		try {
			memberService.join(member2);
		}catch(IllegalStateException e){
			assertThat(e.getMessage()).isEqualTo("�̹� �����ϴ� ȸ���Դϴ�.");	
		}*/
		
		//then
	}
	
	

	@Test
	void testValidateDuplicateMember() {
	
	}

	@Test
	void testFindMembers() {
	
	}

	@Test
	void testFindOne() {
	
	}

}
