package com.qtt.framework.pay.request;

import com.qtt.framework.pay.alipay.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by yanxin on 16/4/13.
 */
public class PayBillRequest {

    private String partner;

    private String seller;

    private String orderId;

    private String notify_url;

    private String sign;

    private String subject;

    private String body;

    private float price;

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPayInfo() {
        String orderInfo = getOrderInfo();
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        return orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
    }

    /**
     * create the order info. 创建订单信息
     *
     */
    private String getOrderInfo() {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + partner + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + seller + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderId + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + notify_url + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, "MIICXAIBAAKBgQDAn+OICm1pDLtbeQrogCm4/gQWGYH/bpkDuX89oHkihgsOlY+I" +
                "1zGU8Sq5GPMXWiG5prjuwT5XZ4xFvxWVNT+Dbt6/xLFBD7ZCeUkGK6MGM/YBkB/F" +
                "f7bhRcjyUq92Iarz+5He22NDZgREP8Uech5waDDo1f6sc++1LSCCze9O6wIDAQAB" +
                "AoGAFQnhmhw5cv3XANipnldRN0lXPOqwTivQPp3mDd8XG/Q0BPXE5ZdLbQ8iMXFO" +
                "K8DGajaRgFJO8tD2ey+xtchJFWtAAY6GKUv4VnzVw8Sx43flE4KsMDqem5jyeM2C" +
                "wa9Oc1Y+EXHc1x8DoZCozQLmArHcuL0ExkXtwyr4rUhbs+ECQQDwPGOPWUsuVxgR" +
                "rFnezLZDWQ7EIR2X/jWq98SAOIKF2wqaxLV/NEMSu2FXxVnGP3F/ch8NmskMwKqf" +
                "xz8sVrdzAkEAzUOzBlwLQzxQoWXcWcvKsLgB5fhmkiS97pMHoGkalt8yGQIajmLl" +
                "SvXkin+Z6H2WsmeS27hjfDY58v78frn8qQJBAMx0mMcHV9S4iuN/q3zS/MuUn9yL" +
                "0oPo9gPrmnEFpxAEu6G8iDsaX3sxvkbUzvLKS+4LUiJh+XnA/3W8PSefJcECQFBl" +
                "dMhH4u1pwVcF84GETZZb6/psCZiyJWY9Vl5g7FYtg4zCAy7D03VBzErm4ZxrHEwy" +
                "PxYqKWUsRBRGBRWJuUkCQH4Rw6VkFIPSrfN12vbRE2MTL5Owg1xnOFTtjISaal4f" +
                "fm4I3RyhCF4B1ielnhopLyeFaxceYhV5roN/vpym+bo=");
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

}
