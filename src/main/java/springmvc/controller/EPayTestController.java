package springmvc.controller;

import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springmvc.ecpay.payment.integration.AllInOne;
import springmvc.ecpay.payment.integration.domain.AioCheckOutApplePay;
import springmvc.ecpay.payment.integration.domain.InvoiceObj;

@Controller
public class EPayTestController {
	
	@RequestMapping(value = "/test")
	public void test(HttpServletResponse response) {
		System.out.println("test");
		System.out.println("test");
		System.out.println("test");
	}

	// 綠界測試
	@RequestMapping(value = "/epaytest")
	@ResponseBody
	public void epaytest(HttpServletResponse response) {
		AllInOne all = new AllInOne("");
		AioCheckOutApplePay obj = new AioCheckOutApplePay();
		obj.setMerchantTradeNo(UUID.randomUUID().toString().toUpperCase().replace("-", "").substring(0,16));
		obj.setMerchantTradeDate("2017/01/01 08:05:23");
		obj.setTotalAmount("50");
		obj.setTradeDesc("test Description");
		obj.setItemName("TestItem");
		obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
		System.out.println(form);
		
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("<html>");
			out.print("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
			out.print("<title>oPay</title>");
			out.print("<body>");
			out.print(form);
			out.print("</body>");
			out.print("</html>");
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
