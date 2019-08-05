package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.dao.ITransaction3Dao;
import com.study.springboot.service.IBuyTicketService;

@Controller
public class MyController {

	@Autowired
	IBuyTicketService buyTicketSrv;
	@Autowired
	TransactionTemplate transactionTemplate;
	@Autowired
	ITransaction3Dao transaction3dao;

	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "Transaction";
	}

	@RequestMapping("/buy_ticket")
	public String buy_ticket() {
		return "buy_ticket";
	}

	@RequestMapping("/buy_ticket_card_tsX")
	public String buy_ticket_card_tsX(@RequestParam("consumerId") String consumerId,
								  @RequestParam("amount") String amount,
								  @RequestParam("error") String error,
								  Model model)
	{
		int nResult = buyTicketSrv.buyTsX(consumerId, Integer.parseInt(amount), error);
		model.addAttribute("consumerId", consumerId);
		model.addAttribute("amount", amount);
		if(nResult == 1) {
			return "buy_ticket_end";
		} else {
			return "buy_ticket_error";
		}
	}

	@RequestMapping("/buy_ticket_card_tsManager")
	public String buy_ticket_card_tsManager(@RequestParam("consumerId") String consumerId,
								  @RequestParam("amount") String amount,
								  @RequestParam("error") String error,
								  Model model)
	{
		int nResult = buyTicketSrv.buyTsManager(consumerId, Integer.parseInt(amount), error);
		model.addAttribute("consumerId", consumerId);
		model.addAttribute("amount", amount);
		if(nResult == 1) {
			return "buy_ticket_end";
		} else {
			return "buy_ticket_error";
		}
	}

	@RequestMapping("/buy_ticket_card_tsTemplate")
	public String buy_ticket_card_tsTemplate(@RequestParam("consumerId") String consumerId,
								  @RequestParam("amount") String amount,
								  @RequestParam("error") String error,
								  Model model)
	{
		int nResult = buyTicketSrv.buyTsTemplate(consumerId, Integer.parseInt(amount), error);
		model.addAttribute("consumerId", consumerId);
		model.addAttribute("amount", amount);
		if(nResult == 1) {
			return "buy_ticket_end";
		} else {
			return "buy_ticket_error";
		}
	}

	@RequestMapping("/buy_ticket_card_tsPropagation")
	public String buy_ticket_card_tsPropagation(@RequestParam("consumerId") String consumerId,
								  @RequestParam("amount") String amount,
								  @RequestParam("error") String error,
								  Model model)
	{
		model.addAttribute("consumerId", consumerId);
		model.addAttribute("amount", amount);

		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {

				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					// 트랜잭션 수행 중에 트랜잭셕수행하는 메서드 호출
					int nResult = buyTicketSrv.buyTsPropagation(consumerId, Integer.parseInt(amount), error);

					//의도적 에러 발생
					if (error.equals("2")) { int n = 10/0 ;}

					transaction3dao.pay(consumerId, Integer.parseInt(amount));;
				}
			});
		} catch (Exception e) {
			System.out.println("[Transaction Propagation #1] Rollback");
			return "buy_ticket_error";
		}
		return "buy_ticket_end";
	}
}
