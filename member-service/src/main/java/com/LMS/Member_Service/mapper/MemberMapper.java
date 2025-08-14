package com.LMS.Member_Service.mapper;

import com.LMS.Member_Service.dto.MemberRequestDTO;
import com.LMS.Member_Service.dto.MemberResponseDTO;
import com.LMS.Member_Service.entity.Member;

public class MemberMapper {
    public static MemberResponseDTO toMemberResponseDTO(Member member) {
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        memberResponseDTO.setMemberId(member.getMemberId().toString());
        memberResponseDTO.setName(member.getName());
        memberResponseDTO.setEmail(member.getEmail());
        memberResponseDTO.setActive(member.isActive().toString());
        memberResponseDTO.setFineAmount(member.getFineAmount().toString());
        return memberResponseDTO;
    }

    public static Member toMember(MemberRequestDTO memberRequestDTO) {
        Member member = new Member();
        member.setName(memberRequestDTO.getName());
        member.setEmail(memberRequestDTO.getEmail());
        member.setActive(memberRequestDTO.isActive());
        member.setFineAmount(memberRequestDTO.getFineAmount());
        return member;
    }
}
