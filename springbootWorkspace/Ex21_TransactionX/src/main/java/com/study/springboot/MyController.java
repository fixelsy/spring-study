package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.service.IBuyTicketService;

@Controller
public class MyController {

	@Autowired
	IBuyTicketService buyTicketSrv;

	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "Transaction X (1)";
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
}
