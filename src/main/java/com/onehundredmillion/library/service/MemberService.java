package com.onehundredmillion.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onehundredmillion.library.domain.Address;
import com.onehundredmillion.library.domain.Member;
import com.onehundredmillion.library.dto.MemberUpdateForm;
import com.onehundredmillion.library.exception.MemberNotFoundException;
import com.onehundredmillion.library.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	// 회원가입
	@Transactional
	public Long join(Member member) {
<<<<<<< HEAD
		String userId = member.getUserId();
		checkIdDuplicate(userId);
=======
		validateDuplicateMember(member); // 중복 회원 검증
		/*
		 * String hashPw = bCryptPasswordEncoder.encode(member.getPassword());
		 * member.setPassword(hashPw);
		 */
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
		memberRepository.save(member);
		return member.getId();
	}

<<<<<<< HEAD
	public Boolean checkIdDuplicate(String userId) {
		return memberRepository.existsByuserId(userId);
=======
	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
	}

	// 로그인
	public Member login(String userId, String password) {
		return memberRepository.findByLoginId(userId).filter(m -> m.getPassword().equals(password)).orElse(null);
	}

	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Member findOne(Long userId) {
		return memberRepository.findOne(userId);
	}

<<<<<<< HEAD
	@Transactional
	public Member updateMember(Long Id, MemberUpdateForm loginMember) {
		Member member = memberRepository.findOne(Id);
		if (member == null) {
			throw new MemberNotFoundException("Member with id " + Id + " not found");
		}
=======
	public Boolean checkIdDuplicate(String userId) {
		return memberRepository.existsByuserId(userId);
	}

	@Transactional
	public Member updateMember(Long Id, MemberUpdateForm loginMember) {
		Member member = memberRepository.findOne(Id);

		if (member == null) {
			throw new MemberNotFoundException("Member with id " + Id + " not found");
		}

		// 업데이트할 필드 설정
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
		member.setName(loginMember.getName());
		Address address = member.getAddress();
		address.setZipcode(loginMember.getZipcode());
		address.setAddr(loginMember.getAddr());
		address.setAddr_etc(loginMember.getAddr_etc());
		address.setAddr_detail(loginMember.getAddr_detail());
		member.setPassword(loginMember.getPassword());
		member.setPhoneNo(loginMember.getPhoneNo());
<<<<<<< HEAD
		return memberRepository.update(member);
	}
=======
		// 다른 필드들도 필요에 따라 업데이트

		// 업데이트된 멤버를 반환
		return memberRepository.update(member);
	}

>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
}