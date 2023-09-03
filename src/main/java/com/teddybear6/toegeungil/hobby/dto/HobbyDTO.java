package com.teddybear6.toegeungil.hobby.dto;

public class HobbyDTO {

    /*  해야할일
     *   최대인원
     *   강사이름
     *   일정(날짜 시간 장소)
     *   카테고리
     *   키워드
     *   가격
     *   마감일
     *   참여자
     *   신청전 확인해주세요
     *   강사 소개
     *   사진 4장
     *   마감여부
     *
     *   마감되었을때 참가자 한하여 후기글 + 점수
     *   선생이 다는 댓글
     *
     * 1.리드 부터 해보자
     * 전체 조회는 사진 제목 가격 카테고리 키워드만 보이면 된다
     */


    private int hobbyCode;


    private String hobbyTitle; //제목

    private String tutorName; //선생 이름

    private int maxPersonnel;  //최대인원

    private String categoryName; //카테고리

    private int hobbyPrice;  // 가격



}
