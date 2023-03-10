package icsdiscover.openai;

import java.util.ArrayList;

record Usage(int prompt_tokens, int completion_tokens, int total_tokens) {
}

record ChatGPTChoice(int index, ChatGPTMessage message, String finish_reason) {
}

record ChatGPTResponse(String id, String object, int created, ArrayList<ChatGPTChoice> choices, Usage usage) {
}