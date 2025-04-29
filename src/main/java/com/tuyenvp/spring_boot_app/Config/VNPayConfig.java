package com.tuyenvp.spring_boot_app.Config;

import com.tuyenvp.spring_boot_app.Util.VNPAYUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Getter
@Component
public class VNPayConfig {
    @Value("${payment.vnPay.url:https://sandbox.vnpayment.vn/paymentv2/vpcpay.html}")
    private String vnp_PayUrl;

    @Value("${payment.vnPay.returnUrl:http://localhost:8088/payment/vnpay-return}")
    private String vnp_ReturnUrl;

    @Value("${payment.vnPay.tmnCode:J9T46OSO}")
    private String vnp_TmnCode;

    @Value("${payment.vnPay.secretKey:6TN1SV78KOVCRK1AF559KGK0VYZOFOP2}")
    private String secretKey;

    @Value("${payment.vnPay.version:2.1.0}")
    private String vnp_Version;

    @Value("${payment.vnPay.command:pay}")
    private String vnp_Command;

    @Value("${payment.vnPay.orderType:billpayment}")
    private String orderType;

    /**
     * Tạo danh sách các tham số cấu hình cho VNPay
     */
    public Map<String, String> getVNPayConfig() {
        Map<String, String> vnpParamsMap = new HashMap<>();
        vnpParamsMap.put("vnp_Version", vnp_Version);
        vnpParamsMap.put("vnp_Command", vnp_Command);
        vnpParamsMap.put("vnp_TmnCode", vnp_TmnCode);
        vnpParamsMap.put("vnp_CurrCode", "VND");
        vnpParamsMap.put("vnp_TxnRef", VNPAYUtil.getRandomNumber(8));
        vnpParamsMap.put("vnp_OrderInfo", "Thanh toan don hang: " + VNPAYUtil.getRandomNumber(8));
        vnpParamsMap.put("vnp_OrderType", orderType);
        vnpParamsMap.put("vnp_Locale", "vn");
        vnpParamsMap.put("vnp_ReturnUrl", vnp_ReturnUrl);

        // Thời gian tạo và hết hạn của giao dịch
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        vnpParamsMap.put("vnp_CreateDate", formatter.format(calendar.getTime()));

        calendar.add(Calendar.MINUTE, 15);
        vnpParamsMap.put("vnp_ExpireDate", formatter.format(calendar.getTime()));

        return vnpParamsMap;
    }
}
