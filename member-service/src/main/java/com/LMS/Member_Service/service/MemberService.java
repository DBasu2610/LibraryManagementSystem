package com.LMS.Member_Service.service;

import com.LMS.Member_Service.dto.MemberRequestDTO;
import com.LMS.Member_Service.dto.MemberResponseDTO;
import com.LMS.Member_Service.entity.Member;
import com.LMS.Member_Service.exception.EmailAlreadyExistsException;
import com.LMS.Member_Service.exception.MemberNotFoundException;
import com.LMS.Member_Service.mapper.MemberMapper;
import com.LMS.Member_Service.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public MemberResponseDTO createMember(MemberRequestDTO memberRequestDTO) {
        if(memberRepository.existsByEmail(memberRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Member with email " + memberRequestDTO.getEmail() + " already exists");
        }
        Member member= MemberMapper.toMember(memberRequestDTO);
        Member savedMember=memberRepository.save(member);
        return MemberMapper.toMemberResponseDTO(savedMember);
    }

    public MemberResponseDTO getMemberById(UUID id){
        Member member=memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member with id " + id + " not found"));
        return MemberMapper.toMemberResponseDTO(member);
    }

    public List<MemberResponseDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(MemberMapper::toMemberResponseDTO).toList();
    }


    public MemberResponseDTO updateMember(UUID id, MemberRequestDTO memberRequestDTO) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member with id " + id + " not found"));

        if (!memberRequestDTO.getEmail().equals(member.getEmail()) && memberRepository.existsByEmail(memberRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Member with email " + memberRequestDTO.getEmail() + " already exists");
        }

        member.setName(memberRequestDTO.getName());
        member.setEmail(memberRequestDTO.getEmail());
        member.setFineAmount(memberRequestDTO.getFineAmount());
        member.setActive(memberRequestDTO.isActive());
        Member updatedMember = memberRepository.save(member);

        return MemberMapper.toMemberResponseDTO(updatedMember);
    }

    public void deleteMember(UUID id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member with id " + id + " not found"));
        memberRepository.delete(member);
    }

    public boolean isMemberValid(String memberId) {
        return memberRepository.existsById(UUID.fromString(memberId));
    }
}
