package icsdiscover.aiweb;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;

@SessionAttributes({ "currentChat" })
@Controller
public class AiwebController {
	private static final Logger log = LoggerFactory.getLogger(AiwebController.class);

	private final static Locale locale = Locale.getDefault();

	@Autowired
	private AiwebService aiwebService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/")
	public String index(AiwebBean aiwebBean, Model model, SessionStatus status) {
		aiwebBean.setTitle(messageSource.getMessage("homeText", null, locale).split("Q:")[0]);
		model.addAttribute("aiwebBean", aiwebBean);
		status.setComplete();

		return "index";
	}

	@ResponseBody
	@PostMapping("/chat")
	public String chat(@ModelAttribute("aiwebBean") AiwebBean aiwebBean, Model model, HttpSession session) {
		log.info("Getting answer from Open AI");
		String prompt = aiwebBean.getPrompt(), response = "Fatal Error";
		try {
			String currentChat = (String) session.getAttribute("currentChat");
			currentChat = (currentChat == null || "".equals(currentChat) ? "" : currentChat) + "\nQ: " + prompt
					+ "\nA: ";
			currentChat += aiwebService.parseUnstructuredData(prompt).trim();
			response = currentChat;
			model.addAttribute("currentChat", currentChat);
		} catch (Exception e) {
			e.printStackTrace();
			response = messageSource.getMessage("apiCallfailed", new String[] { e.getMessage() }, locale);
		}

		return response.trim();
	}
}
