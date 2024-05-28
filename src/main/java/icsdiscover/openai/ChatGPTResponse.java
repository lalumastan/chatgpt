package icsdiscover.openai;

import java.util.ArrayList;

record Usage(int prompt_tokens, int completion_tokens, int total_tokens) {
}

record ChatGPTChoice(int index, ChatGPTMessage message, Object logprobs, String finish_reason) {
}

record ChatGPTResponse(String id, String object, int created, String model, String system_fingerprint, ArrayList<ChatGPTChoice> choices, Usage usage) {
}