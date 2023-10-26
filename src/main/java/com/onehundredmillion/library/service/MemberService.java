package com.onehundredmillion.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onehundredmillion.library.domain.Address;
import com.onehundredmillion.library.domain.Member;
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
		validateDuplicateMember(member); // 중복 회원 검증
		/*
		 * String hashPw = bCryptPasswordEncoder.encode(member.getPassword());
		 * member.setPassword(hashPw);
		 */
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
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

	public Boolean checkIdDuplicate(String userId) {
		return memberRepository.existsByuserId(userId);
	}

	@Transactional
	public void updateUser(Long id, Member member) {
		Member findmember = memberRepository.findOne(id);
		findmember.setName(member.getName());
		findmember.setPassword(member.getPassword());
		findmember.setPasswordConfirm(member.getPasswordConfirm());
		findmember.setPhoneNo(member.getPhoneNo());
		findmember.setRent(member.getRent());

		// Update specific fields within the Address object
		Address address = findmember.getAddress();
		Address newAddress = member.getAddress();

		address.setZipcode(newAddress.getZipcode());
		address.setAddr(newAddress.getAddr());
		address.setAddr_etc(newAddress.getAddr_etc());
		address.setAddr_detail(newAddress.getAddr_detail());

		// Save the updated findmember to the database
		memberRepository.update(findmember);
	}
}