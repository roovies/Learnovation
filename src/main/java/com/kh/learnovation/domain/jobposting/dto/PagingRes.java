package com.kh.learnovation.domain.jobposting.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class PagingRes {
    private int total; // 총 게시물 개수
    private int currentPage; // 현재 페이지
    private int totalNaviCount; // 총 네비게이션 개수
    private int naviCountPerPage = 10; // 페이지당 보여질 네비게이터 개수
    private int startNavi;
    private int endNavi;
    private int maxPage;

    @Builder
    public PagingRes(int total, int currentPage, int totalNaviCount){
        this.total = total;
        this.currentPage = currentPage;
        this.totalNaviCount = totalNaviCount;
        this.startNavi = (((int)Math.ceil((double)currentPage/naviCountPerPage))-1)*naviCountPerPage+1;
        this.endNavi = startNavi + naviCountPerPage - 1;
        this.maxPage = (int)((double)total/20+0.9);
        if(this.endNavi>this.maxPage) {
            this.endNavi = this.maxPage;
        }
    }

}
