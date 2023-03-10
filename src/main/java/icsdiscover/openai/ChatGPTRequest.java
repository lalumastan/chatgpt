package icsdiscover.openai;

import java.util.ArrayList;

record ChatGPTRequest(String model, ArrayList<ChatGPTMessage> messages) {
	ChatGPTRequest(ArrayList<ChatGPTMessage> messages) {
		this("gpt-3.5-turbo", messages);
	}
}
