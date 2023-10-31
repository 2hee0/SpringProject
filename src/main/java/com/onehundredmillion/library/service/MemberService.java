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
		String userId = member.getUserId();
		checkIdDuplicate(userId);
		memberRepository.save(member);
		return member.getId();
	}

	public Boolean checkIdDuplicate(String userId) {
		return memberRepository.existsByuserId(userId);
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

	@Transactional
	public Member updateMember(Long Id, MemberUpdateForm loginMember) {
		Member member = memberRepository.findOne(Id);
		if (member == null) {
			throw new MemberNotFoundException("Member with id " + Id + " not found");
		}
		member.setName(loginMember.getName());
		Address address = member.getAddress();
		address.setZipcode(loginMember.getZipcode());
		address.setAddr(loginMember.getAddr());
		address.setAddr_etc(loginMember.getAddr_etc());
		address.setAddr_detail(loginMember.getAddr_detail());
		member.setPassword(loginMember.getPassword());
		member.setPhoneNo(loginMember.getPhoneNo());
		return memberRepository.update(member);
	}
}