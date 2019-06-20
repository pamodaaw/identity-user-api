package org.wso2.carbon.identity.rest.api.user.challenge.v1.core.functions;

import org.wso2.carbon.identity.recovery.model.ChallengeQuestion;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeQuestionDTO;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeSetDTO;

import java.util.function.Function;

public class ChallengeQuestionToExternal implements Function<ChallengeQuestion, ChallengeQuestionDTO> {

    @Override
    public ChallengeQuestionDTO apply(ChallengeQuestion challengeQuestion) {

        ChallengeQuestionDTO question = new ChallengeQuestionDTO();
        question.setLocale(challengeQuestion.getLocale());
        question.setQuestion(challengeQuestion.getQuestion());
        question.setQuestionId(challengeQuestion.getQuestionId());

        return question;
    }
}
