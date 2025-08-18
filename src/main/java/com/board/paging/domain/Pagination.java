package com.board.paging.domain;

import lombok.Data;

// OFFSET  30            ROWS FETCH NEXT 10 ROWS ONLY   ; 문법
//        limitStart(0~) 

// paging.jsp << <  1         2  3  4         5       > >>
//                 startPage               endPage
//                 startnum                endnum   

@Data
public class Pagination {

    private int      totalRecordCount;     // 검색된 전체 데이터 수
                                           // menu_id 에 해당하는  검색된
                                           // <- boardPagingMapper.count( menuDTO );
    private int      totalPageCount;
    // 전체 페이지 수 -> 
    // totalpageCount = ceil( (double)totalRecordCount / (double)recordSize ) // paging.jsp
    private int      startPage;            // 첫 페이지 번호 -> startnum       // paging.jsp
    private int      endPage;              // 끝 페이지 번호 -> endnum         // paging.jsp 
    private int      limitStart;           // LIMIT 시작 위치 -> offset( (page - 1) * recordSize )
    private boolean  existPrevPage;        // 이전 페이지 존재 여부
    private boolean  existNextPage;        // 다음 페이지 존재 여부

    public Pagination(int totalRecordCount, SearchDTO params) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            calculation(params);
        }
    }

    private void calculation(SearchDTO params) {
        // 
        // 전체 페이지 수 계산
      //  totalPageCount = ((totalRecordCount - 1) / params.getRecordSize()) + 1;
        totalPageCount =  
        	(int) Math.ceil( (double) totalRecordCount / (double) params.getRecordSize() );
        			// 자바 CEIL 은 실수 리턴 그래서 MATH 붙힘
        // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
        // 현제 페이지가 마지막 페이지보다 크면 데이터가 있는 마지막 페이지를 계속 출력  
        if (params.getPage() > totalPageCount) {
            params.setPage(totalPageCount);
        }

        // 첫 페이지 번호 계산 startPage -> startnum
        // startPage =   params.getPage() : nowpage
        startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;
                         //  4 - 1 / 10 * 10 +1
        // 끝 페이지 번호 계산
        endPage   = startPage + params.getPageSize() - 1;

        // 끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        // LIMIT 시작 위치 계산 = offset
        // limitStart = (params.getPage() - 1) * params.getRecordSize();
        
        // 이전 페이지 존재 여부 확인
        // existPrevPage = startPage != 1;

        // 다음 페이지 존재 여부 확인
        // existNextPage = (endPage * params.getRecordSize()) < totalRecordCount;
    }

}
