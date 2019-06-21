/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.rest.api.user.challenge.v1.core;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.base.MultitenantConstants;
import org.wso2.carbon.identity.api.user.challenge.common.Constants;
import org.wso2.carbon.identity.api.user.challenge.common.error.APIError;
import org.wso2.carbon.identity.application.common.model.User;
import org.wso2.carbon.identity.core.util.IdentityUtil;
import org.wso2.carbon.identity.recovery.ChallengeQuestionManager;
import org.wso2.carbon.identity.recovery.IdentityRecoveryException;
import org.wso2.carbon.identity.recovery.model.ChallengeQuestion;
import org.wso2.carbon.identity.recovery.model.UserChallengeAnswer;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.core.functions.ChallengeQuestionToExternal;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.core.functions.UserChallengeAnswerToExternal;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeAnswerDTO;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeQuestionDTO;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeQuestionPatchDTO;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeSetDTO;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.UserChallengeAnswerDTO;
import org.wso2.carbon.user.core.UserStoreConfigConstants;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static org.wso2.carbon.identity.recovery.IdentityRecoveryConstants.LOCALE_EN_US;
import static org.wso2.carbon.identity.api.user.challenge.common.Constants.OPERATION_ADD;

public class UserChallengeService {
    private static final Log log = LogFactory.getLog(UserChallengeService.class);
    private static ChallengeQuestionManager questionManager = ChallengeQuestionManager.getInstance();
    public static final String WSO2_CLAIM_DIALECT = "http://wso2.org/claims/";

    public String getTenantDomainFromContext() {

        String tenantDomain = MultitenantConstants.SUPER_TENANT_DOMAIN_NAME;
        if (IdentityUtil.threadLocalProperties.get().get(Constants.TENANT_NAME_FROM_CONTEXT) != null) {
            tenantDomain = (String) IdentityUtil.threadLocalProperties.get().get(Constants.TENANT_NAME_FROM_CONTEXT);
        }
        return tenantDomain;
    }

    public List<ChallengeSetDTO> getChallengesForUser(String userId, Integer offset, Integer limit) {

        User user = extractUser(userId);
        try {
            return buildChallengesDTO(questionManager.getAllChallengeQuestionsForUser(getTenantDomainFromContext(), user),
                    offset, limit);
        } catch (IdentityRecoveryException e) {
            //TODO handle and throw correct error
            throw new APIError(Response.Status.INTERNAL_SERVER_ERROR, new Error.Builder().withCode("somecodee")
                    .withMessage("some message").withDescription("some description").build());
        }

    }

    public boolean setChallengeAnswersOfUser(String userId, List<ChallengeAnswerDTO> challengeAnswers) {

        User user = extractUser(userId);
        List<UserChallengeAnswer> answers = buildChallengeAnswers(challengeAnswers);
        try {
            questionManager.setChallengesOfUser(user, answers.toArray(new UserChallengeAnswer[answers.size()]));
        } catch (IdentityRecoveryException e) {
            //TODO handle and throw correct error
            throw new APIError(Response.Status.INTERNAL_SERVER_ERROR , new Error.Builder().withCode("somecodee")
                    .withMessage("some message").withDescription("some description").build());

        }
        return true;
    }

    public List<UserChallengeAnswerDTO> getChallengeAnswersOfUser(String userId) {

        User user = extractUser(userId);
        try {
            return getUserChallengeAnswerDTOsOfUser(user);
        } catch (IdentityRecoveryException e) {
            //TODO handle and throw correct error
            throw new APIError(Response.Status.INTERNAL_SERVER_ERROR , new Error.Builder().withCode("somecodee")
                    .withMessage("some message").withDescription("some description").build());
        }
    }


    public boolean deleteQuestionSet(String challengeSetId, String locale) throws IdentityRecoveryException {

        if (StringUtils.isEmpty(locale)) {
            locale = StringUtils.EMPTY;
        }
//        questionManager.deleteChallengeQuestionSet(challengeSetId, locale, getTenantDomainFromContext());
        return true;
    }

    public boolean addChallengeSets(List<ChallengeSetDTO> challengeSets) throws
            IdentityRecoveryException {

        ChallengeQuestion[] toAdd = buildChallengeQuestionSets(challengeSets);

        questionManager.addChallengeQuestions(toAdd, getTenantDomainFromContext());
        return true;
    }

    public boolean updateChallengeSets(String challengeSetId, List<ChallengeQuestionDTO> challenges) throws
            IdentityRecoveryException {

        deleteQuestionSet(challengeSetId, null);

        List<ChallengeQuestion> questions = buildChallengeQuestions(challenges, challengeSetId);
        ChallengeQuestion[] toPut = questions.toArray(new ChallengeQuestion[questions.size()]);
        questionManager.addChallengeQuestions(toPut, getTenantDomainFromContext());
        return true;
    }

    public boolean patchChallengeSet(String challengeSetId, ChallengeQuestionPatchDTO
            challengeQuestionPatchDTO) throws
            IdentityRecoveryException {

        if (OPERATION_ADD.equalsIgnoreCase(challengeQuestionPatchDTO.getOperation())) {
            List<ChallengeQuestionDTO> challenges = new ArrayList<>();
            ChallengeQuestionDTO challengeQuestion = challengeQuestionPatchDTO.getChallengeQuestion();
            challenges.add(challengeQuestion);
            List<ChallengeQuestion> questions = buildChallengeQuestions(challenges, challengeSetId);

            ChallengeQuestion[] toPatch = questions.toArray(new ChallengeQuestion[questions.size()]);

            questionManager.addChallengeQuestions(toPatch, getTenantDomainFromContext());
            return true;
        } else {
            //TODO throw correct error
            throw new WebApplicationException();
        }

    }

    private User extractUser(String userId) {
        String decodedUsername = new String(Base64.getDecoder().decode(userId));

        if (StringUtils.isBlank(userId)) {
            //TODO throw correct error
            throw new WebApplicationException();
        }
        String[] strComponent = decodedUsername.split("/");

        String username;
        String realm = UserStoreConfigConstants.PRIMARY;

        if (strComponent.length == 1) {
            username = strComponent[0];
        } else if (strComponent.length == 2) {
            realm = strComponent[0];
            username = strComponent[1];
        } else {
            //TODO throw correct error
            throw new APIError(Response.Status.BAD_REQUEST, new Error.Builder().withCode("").withMessage("Invalid " +
                    "User ID provided").withDescription("Provided Username is not in the format of "));
        }

        String tenantDomain = getTenantDomainFromContext();

        User user = new User();
        user.setUserName(username);
        user.setUserStoreDomain(realm);
        user.setTenantDomain(tenantDomain);

        return user;
    }

    private List<UserChallengeAnswerDTO> getUserChallengeAnswerDTOsOfUser(User user) throws IdentityRecoveryException {
        UserChallengeAnswer[] answers = questionManager.getChallengeAnswersOfUser(user);
        return Arrays.stream(answers).map(new UserChallengeAnswerToExternal()).collect(Collectors.toList());
    }

    private ChallengeQuestion[] buildChallengeQuestionSets(List<ChallengeSetDTO> challengeSets) {
        List<ChallengeQuestion> questions = new ArrayList<>();
        for (ChallengeSetDTO challengeSet : challengeSets) {
            String setId = challengeSet.getQuestionSetId();
            questions = buildChallengeQuestions(challengeSet.getQuestions(), setId);
        }
        return questions.toArray(new ChallengeQuestion[questions.size()]);
    }

    private List<ChallengeQuestion> buildChallengeQuestions(List<ChallengeQuestionDTO> challengeSet, String setId) {
        List<ChallengeQuestion> questions = new ArrayList<>();
        for (ChallengeQuestionDTO q : challengeSet) {
            if (StringUtils.isBlank(q.getLocale())) {
                q.setLocale(LOCALE_EN_US);
            }
            questions.add(createChallenceQuestion(setId, q));
        }
        return questions;
    }

    private ChallengeQuestion createChallenceQuestion(String setId, ChallengeQuestionDTO q) {
        return new ChallengeQuestion(WSO2_CLAIM_DIALECT + setId, q.getQuestionId(), q.getQuestion(), q
                .getLocale());
    }

    private List<ChallengeSetDTO> buildChallengesDTO(List<ChallengeQuestion> challengeQuestions, Integer offset,
                                                     Integer limit) {

        Map<String, List<ChallengeQuestion>> challengeSets = groupChallenges(challengeQuestions);
        return challengeSets.entrySet().stream().map((e) ->
                getChallengeSetDTO(e.getKey(), e.getValue())
        ).collect(Collectors.toList());
    }

    private ChallengeSetDTO getChallengeSetDTO(String questionSetId, List<ChallengeQuestion> questions) {
        ChallengeSetDTO challenge = new ChallengeSetDTO();
        challenge.setQuestionSetId(questionSetId);
        List<ChallengeQuestionDTO> questionDTOs = questions.stream().map(new ChallengeQuestionToExternal()).collect(
                Collectors.toList());
        challenge.setQuestions(questionDTOs);
        return challenge;
    }

    private ChallengeSetDTO buildChallengeDTO(List<ChallengeQuestion> challengeQuestions, String
            challengeSetId, Integer offset, Integer limit) {

        List<ChallengeQuestion> challengeSets = filterChallengesBySetId(challengeQuestions, challengeSetId);
        return getChallengeSetDTO(challengeSetId, challengeSets);
//        Map<String, List<ChallengeQuestion>> challengeSets = groupChallenges(challengeQuestions);
//
//        ChallengeSetDTO challenge;
//        if (challengeSets.containsKey(challengeSetId)) {
//            List<ChallengeQuestion> questions = challengeSets.get(challengeSetId);
//            challenge = getChallengeSetDTO(challengeSetId, questions);
//        } else {
//            //TODO throw correct error
//            throw new WebApplicationException();
//        }
//        return challenge;
    }

    private List<UserChallengeAnswer> buildChallengeAnswers(List<ChallengeAnswerDTO> challengeAnswer) {

        return challengeAnswer.stream().map((q) ->
                new UserChallengeAnswer(
                        createChallenceQuestion(q.getQuestionSetId(), q.getChallengeQuestion()),
                        q.getAnswer()))
                .collect(Collectors.toList());
//        List<UserChallengeAnswer> answers = new ArrayList<>();
//        for (ChallengeAnswerDTO ans : challengeAnswer) {
//            UserChallengeAnswer answer = new UserChallengeAnswer();
//            answer.setAnswer(ans.getAnswer());
//            answer.setQuestion(createChallenceQuestion(ans.getQuestionSetId(), ans.getChallengeQuestion()));
//            answers.add(answer);
//        }
//        return answers;
    }

//    private List<ChallengeQuestionDTO> getChallengeQuestionDTOs(List<ChallengeQuestion> questions) {
//        List<ChallengeQuestionDTO> questionDTOs = new ArrayList<>();
//        for (ChallengeQuestion q : questions) {
//            ChallengeQuestionDTO question = new ChallengeQuestionDTO();
//            question.setLocale(q.getLocale());
//            question.setQuestion(q.getQuestion());
//            question.setQuestionId(q.getQuestionId());
//            questionDTOs.add(question);
//        }
//        return questionDTOs;
//    }

    private Map<String, List<ChallengeQuestion>> groupChallenges(List<ChallengeQuestion> challengeQuestions) {
        return challengeQuestions.stream()
                .collect(groupingBy(question -> question.getQuestionSetId().split(WSO2_CLAIM_DIALECT)[1]));
    }

    private List<ChallengeQuestion> filterChallengesBySetId(List<ChallengeQuestion> challengeQuestions, String setId) {
        return challengeQuestions.stream()
                .filter(question -> question.getQuestionSetId().split(WSO2_CLAIM_DIALECT)[1].equals(setId))
                .collect(Collectors.toList());
    }
}
