package com.LMS.Member_Service.controller;

import com.LMS.Member_Service.dto.MemberRequestDTO;
import com.LMS.Member_Service.dto.MemberResponseDTO;
import com.LMS.Member_Service.service.MemberService;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<?> getAllMembers (){
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable UUID id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PostMapping
    public ResponseEntity<?> createMember(@Validated(Default.class) @RequestBody MemberRequestDTO memberRequestDTO) {
        MemberResponseDTO memberResponseDTO=memberService.createMember(memberRequestDTO);
        return ResponseEntity.ok().body(memberResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable UUID id, @Validated(Default.class) @RequestBody MemberRequestDTO memberRequestDTO) {
        MemberResponseDTO updatedMember = memberService.updateMember(id, memberRequestDTO);
        return ResponseEntity.ok(updatedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable UUID id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok().build();
    }
}
