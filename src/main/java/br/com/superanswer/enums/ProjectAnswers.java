package br.com.superanswer.enums;

import org.mockito.stubbing.Answer;

import br.com.superanswer.answers.PowerAnswer;

public enum ProjectAnswers {

	POWER_ANSWER(PowerAnswer.build());
	
	private Answer<Object> answer;

	private ProjectAnswers(Answer<Object> answer) {
		this.answer = answer;
	}

	public Answer<Object> get() {
		return answer;
	}
	
}
