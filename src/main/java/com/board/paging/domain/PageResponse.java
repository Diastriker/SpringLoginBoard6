package com.board.paging.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

// T : 모든 클라스 받을수 있음 

@Data
public class PageResponse<T> {
	//현제 페이지에(nowpage) 보여줄 db 자료 : select 결과
	private List<T>    list = new ArrayList<>();
	
	private Pagination pagination; // Page 에 필요한 변수들을 담아놓은 Class

	public PageResponse(List<T> list, Pagination pagination) {
		super();
		this.list = list;
		this.pagination = pagination;
	}
	
}
