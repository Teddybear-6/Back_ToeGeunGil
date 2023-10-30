package com.teddybear6.toegeungil.community.controller;
import com.teddybear6.toegeungil.auth.dto.AuthUserDetail;
import com.teddybear6.toegeungil.category.entity.Category;
import com.teddybear6.toegeungil.community.dto.CommunityDTO;
import com.teddybear6.toegeungil.community.dto.CommunityKeywordDTO;
import com.teddybear6.toegeungil.community.entity.Community;
import com.teddybear6.toegeungil.community.service.CommunityService;
import com.teddybear6.toegeungil.keyword.entity.Keyword;
import com.teddybear6.toegeungil.local.entity.Local;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/communitys")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "커뮤니티' Api", tags = {"03. Community Info"}, description = "커뮤니티 Api")
@ApiResponses({
        @ApiResponse(code = 200,message = "성공"),
        @ApiResponse(code = 404,message = "잘못된 접근") ,
        @ApiResponse(code = 500,message = "서버에러")
})
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping // 커뮤니티 전체 조회
    @ApiOperation(value = "커뮤니티 전체 조회 Api", notes = "커뮤니티 전체 목록을 조회한다.")
    public ResponseEntity<List<?>> findAllCommunity(final Pageable pageable) {
        List<CommunityDTO> communityList = communityService.findAllCommunity(pageable);

        if (communityList.isEmpty()) {
            return ResponseEntity.status(404).body(Collections.singletonList("error"));
        } else {
            return ResponseEntity.ok().body(communityList);
        }
    }


    @GetMapping("/{communityNum}") // 커뮤니티 번호로 부분 조회
    @ApiOperation(value = "커뮤니티 단일 조회 Api", notes = "커뮤니티 게시글 번호로 해당 게시글을 조회한다.")
    public ResponseEntity<Object> findByCommunityCode(@PathVariable int communityNum){
        Community community = communityService.findByCommunityCode(communityNum);

        if(Objects.isNull(community)) {
            return ResponseEntity.status(404).body(new String("잘못된 코드 입력"));
        }else {
            CommunityDTO communityDTO = new CommunityDTO(community);
            List<Keyword> keywordList = new ArrayList<>();
            for (int i = 0; i < community.getCommunityKeywordList().size(); i++) {
                keywordList.add(community.getCommunityKeywordList().get(i).getKeyword());
            }
            List<CommunityKeywordDTO> communityKeywordDTOList = keywordList.stream().map(m -> new CommunityKeywordDTO(m)).collect(Collectors.toList());
            communityDTO.setCommunityKeywordDTOList(communityKeywordDTOList);

            return ResponseEntity.ok().body(communityDTO);
        }
    }

    @PostMapping // 커뮤니티 등록하기
    @PreAuthorize("hasAnyRole('ADMIN', 'TUTOR', 'USER')")
    @ApiOperation(value = "커뮤니티 작성 Api", notes = "커뮤니티 게시글을 작성한다.")
    public ResponseEntity<?> registCommunity(@RequestBody CommunityDTO communityDTO, @AuthenticationPrincipal AuthUserDetail userDetail) {

        communityDTO.setUserNum(userDetail.getUserEntity().getUserNo());
        communityDTO.setPostWriteDate(new Date());
        int result = 0;
        try {
            result = communityService.registCommunity(communityDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result > 0) {
            return ResponseEntity.ok().body("커뮤니티 등록에 성공하였습니다.");
        } else {
            return ResponseEntity.status(500).body("커뮤니티 등록에 실패하였습니다.");
        }
    }

    @PutMapping("/{communityNum}")// 커뮤니티 수정
    @PreAuthorize("hasAnyRole('ADMIN', 'TUTOR', 'USER')")
    @ApiOperation(value = "커뮤니티 수정 Api", notes = "커뮤니티 게시글을 수정한다.")
    public ResponseEntity<?> updateCommunity(@RequestBody CommunityDTO communityDTO){

        // 유효성 검사 체크 (RequestParam)으로 코드만 받아오고 나머지는 service 로직에서 찾아오기 .,...

        Community findCommunity = communityService.findByCommunityCode(communityDTO.getCommunityNum());

        if(Objects.isNull(findCommunity)){
            return ResponseEntity.status(404).body("커뮤니티 카테고리가 존재하지 않습니다.");
        }

        CommunityDTO community = communityDTO;

        // 직접 각 필드의 값을 전달하여 communityUpdate 메서드를 호출한다.
        int result = communityService.communityUpdate(findCommunity, community);

        if(result > 0){
            return ResponseEntity.ok().body("커뮤니티 수정에 성공했습니다.");
        } else {
            return ResponseEntity.status(400).body("커뮤니티 수정에 실패하였습니다.");
        }
    }

    @DeleteMapping("/{communityNum}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TUTOR', 'USER')")
    @ApiOperation(value = "커뮤니티 삭제 Api", notes = "커뮤니티 게시글을 삭제한다.")
    public ResponseEntity<?> deleteCommunity(@PathVariable int communityNum){

        Community community = communityService.findByCommunityCode(communityNum);

        if(Objects.isNull(community)){
            return ResponseEntity.status(404).body("커뮤니티가 존재하지 않습니다.");
        }

        int result = communityService.deleteCommunityId(communityNum);

        if(result > 0){
            return ResponseEntity.ok().body("커뮤니티가 삭제 완료되었습니다.");
        }else {
            return ResponseEntity.status(500).body("커뮤니티가 삭제되지 않았습니다.");
        }
    }

    @GetMapping("/category/{categoryCode}")
    @ApiOperation(value = "카테고리별 커뮤니티 조회 Api", notes = "카테고리 번호로 카테고리별 해당 커뮤니티 게시글 목록을 조회한다.")
    public ResponseEntity<List<?>> findCommunityCategory(@PathVariable int categoryCode, final Pageable pageable){
        Category category = communityService.findCommunityCategory(categoryCode);

        List<CommunityDTO> communityDTOList = communityService.findCommunityCategoryCode(categoryCode, pageable);

        return ResponseEntity.ok().body(communityDTOList);
    }

    @GetMapping("/category/{categoryCode}/size")
    @ApiOperation(value = "카테고리별 커뮤니티 사이즈 조회 Api", notes = "카테고리 번호로 카테고리별 해당 커뮤니티 게시글들의 사이즈를 조회한다.")
    public ResponseEntity<?> findCommunityCategorySize(@PathVariable int categoryCode, final Pageable pageable){
        Category category = communityService.findCommunityCategory(categoryCode);

        List<CommunityDTO> commuintyList = communityService.findCommunityCategoryCode(categoryCode, pageable);

        return ResponseEntity.ok().body(commuintyList.size());
    }

    @GetMapping("/local/{localCode}")
    @ApiOperation(value = "지역별 커뮤니티 조회 Api", notes = "지역 번호로 지역별 해당 커뮤니티 게시글 목록을 조회한다.")
    public ResponseEntity<List<?>> findCommunityLocal(@PathVariable int localCode, final Pageable pageable){
        Local local = communityService.findCommunityLocal(localCode);

        List<CommunityDTO> communityDTOList = communityService.findCommunityLocalCode(localCode, pageable);

        return ResponseEntity.ok().body(communityDTOList);
    }

    @GetMapping("/local/{localCode}/size")
    @ApiOperation(value = "지역별 커뮤니티 사이즈 조회 Api", notes = "지역 번호로 지역별 해당 커뮤니티 게시글들의 사이즈를 조회한다.")
    public ResponseEntity<?> findCommunityLocalSize(@PathVariable int localCode, final Pageable pageable){
        Local local = communityService.findCommunityLocal(localCode);

        List<CommunityDTO> commuintyList = communityService.findCommunityLocalCode(localCode, pageable);

        return ResponseEntity.ok().body(commuintyList.size());
    }


    @GetMapping("/category/{categoryCode}/local/{localCode}")
    @ApiOperation(value = "카테고리 AND 지역 커뮤니티 조회 Api", notes = "카테고리 번호와 지역 변호로 두 조건에 모두 해당되는 커뮤니티 게시글 목록을 조회한다.")
    public ResponseEntity<List<?>> findCommunityFilterCategoryAndLocal(@PathVariable int categoryCode, @PathVariable int localCode, final Pageable pageable){
        Category category = communityService.findCommunityCategory(categoryCode);
        Local local = communityService.findCommunityLocal(localCode);

        List<CommunityDTO> community = communityService.findCommunityFilterCategoryAndLocal(category, local);

        return ResponseEntity.ok().body(community);
    }

    @GetMapping("/category/{categoryCode}/local/{localCode}/size")
    @ApiOperation(value = "카테고리 AND 지역 커뮤니티 조회 Api", notes = "카테고리 번호와 지역 번호로 두 조건에 모두 해당되는 커뉴니티 게시글 목록을 조회한다.")
    public ResponseEntity<?> findCommunityFilterCategoryAndLocalSize(@PathVariable int categoryCode, @PathVariable int localCode, final Pageable pageable){
        Category category = communityService.findCommunityCategory(categoryCode);
        Local local = communityService.findCommunityLocal(localCode);

        List<CommunityDTO> community = communityService.findCommunityFilterCategoryAndLocal(category, local);

        return ResponseEntity.ok().body(community.size());
    }

    @GetMapping("/size")
    @ApiOperation(value = "커뮤니티 전체 사이즈 조회 Api", notes = "커뮤니티 전체 목록의 사이즈를 조회한다.")
    public ResponseEntity<?> communitySize(){
        List<Community> communityList = communityService.findAllCommunitySize();
        return ResponseEntity.ok().body(communityList.size());
    }

    @GetMapping("/search")
    @ApiOperation(value = "커뮤니티 검색 Api", notes = "검색어를 통해 해당되는 커뮤니티의 제목을 조회한다.")
    public ResponseEntity<List<?>> communitySearch(@RequestParam(name = "communityTitle") String communityTitle, final Pageable pageable){
        List<CommunityDTO> communityDTOList = communityService.findCommunityBycommunityTitleContaining(pageable, communityTitle);

        if (communityDTOList.size() == 0){
            List<String> error = new ArrayList<>();
            error.add(null);
            return ResponseEntity.status(500).body(error);
        }else {
            return ResponseEntity.ok().body(communityDTOList);
        }
    }
}
