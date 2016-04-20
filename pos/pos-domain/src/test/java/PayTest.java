import com.alipay.api.response.AlipayTradePayResponse;
import kj.pos.service.payTreasure.f2fpay.ToAlipayBarTradePay;
import org.junit.Test;

/**
 * Created by Yan on 16-4-20.
 */
public class PayTest {

    @Test
    public void testPay(){
        ////////////////////////////////////请求参数//////////////////////////////////////

        //商户订单号
        String out_trade_no = "MDSY001";
        //商户网站订单系统中唯一订单号，必填

        //订单名称
        String subject = "门店收银Test";
        //必填


        //付款金额
        String total_amount = "0.01";
        //必填

        //付款条码
        String auth_code = "286034257980911429";
        //必填


        //////////////////////////////////////////////////////////////////////////////////

        //把请求参数打包成数组
        AlipayTradePayResponse res = ToAlipayBarTradePay.barPay(out_trade_no, auth_code, total_amount, subject);


        System.out.println(res.getBody());
    }
}
