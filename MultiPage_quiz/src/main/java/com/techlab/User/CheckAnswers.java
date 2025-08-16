package com.techlab.User;

import java.util.List;
import java.util.Map;

import com.techlab.Entity.Question;

public class CheckAnswers {

	public static int getMarks(Map<String, String> userSubmission, List<Question> questions) {
		int marks = 0;
		for(Question q : questions) {
			String userAns = userSubmission.get(q.getQuestion());
			if(q.getcOpt().equals(userAns)) {
				marks++;
			}
		}
		return marks;
	}

}
