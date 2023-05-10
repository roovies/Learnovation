package com.kh.learnovation.domain.jobposting.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DetailResp {
    private String corpNm;
    private String reperNm;
    private String totPsncnt;
    private String yrSalesAmt;
    private String indTpCdNm;
    private String busiSize;
    private String corpAddr;
    private String relJobsNm;
    private String enterTpNm;
    private String empTpNm;
    private String jobCont;
    private String collectPsncnt;
    private String eduNm;
    private String salTpNm;
    private String selMthd;
    private String rcptMthd;
    private String submitDoc;
    private String workRegion;
    private String workdayWorkhrCont;
    private String fourIns;
    private String retirepay;
    private String etcWelfare;
    private String dtlRecrContUrl;

    @Builder
    public DetailResp(String corpNm, String reperNm, String totPsncnt, String yrSalesAmt, String indTpCdNm, String busiSize, String corpAddr,
                      String relJobsNm, String enterTpNm, String empTpNm, String jobCont, String collectPsncnt, String eduNm,
                      String salTpNm, String selMthd, String rcptMthd, String submitDoc, String workRegion, String workdayWorkhrCont,
                      String fourIns, String retirepay, String etcWelfare, String dtlRecrContUrl){
        this.corpNm = corpNm;
        this.reperNm = reperNm;
        this.totPsncnt = totPsncnt;
        this.yrSalesAmt = yrSalesAmt;
        this.indTpCdNm = indTpCdNm;
        this.busiSize = busiSize;
        this.corpAddr = corpAddr;
        this.relJobsNm = relJobsNm;
        this.enterTpNm = enterTpNm;
        this.empTpNm = empTpNm;
        this.jobCont = jobCont;
        this.collectPsncnt = collectPsncnt;
        this.eduNm = eduNm;
        this.salTpNm = salTpNm;
        this.selMthd = selMthd;
        this.rcptMthd = rcptMthd;
        this.submitDoc = submitDoc;
        this.workRegion = workRegion;
        this.workdayWorkhrCont = workdayWorkhrCont;
        this.fourIns = fourIns;
        this.retirepay = retirepay;
        this.etcWelfare = etcWelfare;
        this.dtlRecrContUrl = dtlRecrContUrl;

    }
}
