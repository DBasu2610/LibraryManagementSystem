package com.LMS.Member_Service.grpc;

import com.LMS.Member_Service.service.MemberService;
import io.grpc.stub.StreamObserver;
import library.MemberValidationRequest;
import library.MemberValidationResponse;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class MemberServiceGrpc extends library.MemberServiceGrpc.MemberServiceImplBase {
    Logger logger = LoggerFactory.getLogger(MemberServiceGrpc.class.getName());
    private  final MemberService memberService;

    @Autowired
    public MemberServiceGrpc(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public  void validateMember(MemberValidationRequest request, StreamObserver<MemberValidationResponse> responseObserver) {
        logger.info("Received gRPC request to validate member ID: {}", request.getMemberId());
        boolean isValid = memberService.isMemberValid(request.getMemberId());
        MemberValidationResponse response = MemberValidationResponse.newBuilder()
                .setValid(isValid)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
