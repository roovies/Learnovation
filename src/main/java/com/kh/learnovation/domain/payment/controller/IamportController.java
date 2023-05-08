package com.kh.learnovation.domain.payment.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@Controller
@RequestMapping("/verifyIamport")
public class IamportController {

    private IamportClient api;

    public IamportController() {
        this.api = new IamportClient("6801008464214830", "6ztuxkE2SLK7UOeRNw9OxrUsBb37QYbL2yUaLHYB6VTdpcQyFIN5eWqa4ZgaOtjz56WR2O4Urm4mKXLh");
    }

    @ResponseBody
    @PostMapping(value = "/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid
            (
                    Model model
                    , Locale locale
                    , HttpSession session
                    ,@PathVariable(value = "imp_uid") String imp_uid

            ) throws IamportResponseException, IOException
    {
        return api.paymentByImpUid(imp_uid);
    }

}
