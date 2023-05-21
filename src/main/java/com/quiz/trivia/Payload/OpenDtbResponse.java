package com.quiz.trivia.Payload;

import lombok.Data;

@Data
public class OpenDtbResponse {

   private int response_code;
   private QuestionDownloadResponse[] results;

}
