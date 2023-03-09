package icsdiscover.aiweb;

import java.util.ArrayList;

record ChatGPTRequest(String model, ArrayList<Message> messages) {
	ChatGPTRequest(ArrayList<Message> messages) {
	      this("gpt-3.5-turbo", messages); 
    }
}
