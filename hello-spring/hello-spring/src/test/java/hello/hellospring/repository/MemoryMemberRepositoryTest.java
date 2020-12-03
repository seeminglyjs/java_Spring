package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import hello.hellospring.domain.Member;

class MemoryMemberRepositoryTest {
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach
	public void afterEach() {
		repository.clearStore();
		//하나의 test 가 끝날때마다 공용데이터베이스를 깨끗이 청소해주는 메서드
		// TDD 테스트 주도 개발이라고 해서 테스트 케이스를 먼저 만들어두고 이후
		// 구현0 클래스를 만드는 개발방법도 있다.
	}
	
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
		Assertions.assertEquals(member, result);
		assertThat(member).isEqualTo(result);
		System.out.println("result = " +(result == member));
		
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2); 
 
		Member result = repository.findByName("spring1").get();
		assertThat(result).isEqualTo(member1);
	}

	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(2);
	}
	
	
}
