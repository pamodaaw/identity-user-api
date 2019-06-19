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
import org.wso2.carbon.identity.application.common.model.User;
import org.wso2.carbon.identity.core.util.IdentityUtil;
import org.wso2.carbon.identity.recovery.ChallengeQuestionManager;
import org.wso2.carbon.identity.recovery.IdentityRecoveryException;
import org.wso2.carbon.identity.recovery.model.ChallengeQuestion;
import org.wso2.carbon.identity.recovery.model.UserChallengeAnswer;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeAnswerDTO;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeQuestionDTO;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeQuestionPatchDTO;
import org.wso2.carbon.identity.rest.api.user.challenge.v1.dto.ChallengeSetDTO;
import org.wso2.carbon.user.core.UserStoreConfigConstants;

import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.wso2.carbon.identity.recovery.IdentityRecoveryConstants.LOCALE_EN_US;
import static org.wso2.carbon.identity.rest.api.user.challenge.v1.core.Constants.OPERATION_ADD;

public class ChallengeService {
    private static Log log = LogFactory.getLog(ChallengeService.class);
    private static ChallengeQuestionManager questionManager = ChallengeQuestionManager.getInstance();
    public static final String WSO2_CLAIM_DIALECT = "http://wso2.org/claims/";

    public static String getTenantDomainFromContext() {

        String tenantDomain = MultitenantConstants.SUPER_TENANT_DOMAIN_NAME;
        if (IdentityUtil.threadLocalProperties.get().get(Constants.TENANT_NAME_FROM_CONTEXT) != null) {
            tenantDomain = (String) IdentityUtil.threadLocalProperties.get().get(Constants.TENANT_NAME_FROM_CONTEXT);
        }
        return tenantDomain;
    }

    public static List<ChallengeSetDTO> getChallenges(String locale, Integer offset, Integer limit) throws IdentityRecoveryException {

        if (StringUtils.isEmpty(locale)) {
            return buildChallengesDTO(questionManager.getAllChallengeQuestions(getTenantDomainFromContext()),
                    offset, limit);
        } else {
            return buildChallengesDTO(questionManager.getAllChallengeQuestions(getTenantDomainFromContext(), locale),
                    offset, limit);
        }

    }

    public static List<ChallengeSetDTO> getChallengesForUser(String userId, Integer offset, Integer limit) throws IdentityRecoveryException {

        User user = getUserFromRequest(userId);
        return buildChallengesDTO(questionManager.getAllChallengeQuestionsForUser(getTenantDomainFromContext(), user),
                offset, limit);

    }

    public static boolean setChallengeAnswersOfUser(String userId, List<ChallengeAnswerDTO>
            challengeAnswers) throws
            IdentityRecoveryException {

        User user = getUserFromRequest(userId);
        List<UserChallengeAnswer> answers = buildChallengeAnswers(challengeAnswers);
        questionManager.setChallengesOfUser(user, answers.toArray(new UserChallengeAnswer[answers.size()]));
        return true;
    }

    public static List<ChallengeAnswerDTO> getChallengeAnswersOfUser(String userId) throws
            IdentityRecoveryException {

        User user = getUserFromRequest(userId);
        UserChallengeAnswer[] answers = questionManager.getChallengeAnswersOfUser(user);
        return buildChallengeAnswerDTOs(answers);
    }

    public static ChallengeSetDTO getChallengeSet(String locale, String challengeSetId, Integer offset, Integer
            limit) throws IdentityRecoveryException {

        if (StringUtils.isEmpty(locale)) {
            return buildChallengeDTO(questionManager.getAllChallengeQuestions(getTenantDomainFromContext()),
                    challengeSetId,
                    offset, limit);
        } else {
            return buildChallengeDTO(questionManager.getAllChallengeQuestions(getTenantDomainFromContext(), locale),
                    challengeSetId, offset, limit);
        }

    }

    public static boolean deleteQuestion(String challengeSetId, String questionId, String locale) throws
            IdentityRecoveryException {

        if (StringUtils.isEmpty(locale)) {
            locale = StringUtils.EMPTY;
        }
        ChallengeQuestion[] toDelete = {new ChallengeQuestion(challengeSetId, questionId, StringUtils.EMPTY, locale)};
        questionManager.deleteChallengeQuestions(toDelete, getTenantDomainFromContext());
        return true;
    }

    public static boolean deleteQuestionSet(String challengeSetId, String locale) throws IdentityRecoveryException {

        if (StringUtils.isEmpty(locale)) {
            locale = StringUtils.EMPTY;
        }
        questionManager.deleteChallengeQuestionSet(challengeSetId, locale, getTenantDomainFromContext());
        return true;
    }

    public static boolean addChallengeSets(List<ChallengeSetDTO> challengeSets) throws
            IdentityRecoveryException {

        ChallengeQuestion[] toAdd = buildChallengeQuestionSets(challengeSets);

        questionManager.addChallengeQuestions(toAdd, getTenantDomainFromContext());
        return true;
    }

    public static boolean patchChallengeSet(String challengeSetId, ChallengeQuestionPatchDTO
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

    public static boolean updateChallengeSets(String challengeSetId, List<ChallengeQuestionDTO> challenges) throws
            IdentityRecoveryException {

        deleteQuestionSet(challengeSetId, null);

        List<ChallengeQuestion> questions = buildChallengeQuestions(challenges, challengeSetId);
        ChallengeQuestion[] toPut = questions.toArray(new ChallengeQuestion[questions.size()]);
        questionManager.addChallengeQuestions(toPut, getTenantDomainFromContext());
        return true;
    }

    private static User getUserFromRequest(String userId) {
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
            throw new WebApplicationException();
        }

        String tenantDomain = getTenantDomainFromContext();

        User user = new User();
        user.setUserName(username);
        user.setUserStoreDomain(realm);
        user.setTenantDomain(tenantDomain);

        return user;
    }

    private static ChallengeQuestion[] buildChallengeQuestionSets(List<ChallengeSetDTO> challengeSets) {
        List<ChallengeQuestion> questions = new ArrayList<>();
        for (ChallengeSetDTO challengeSet : challengeSets) {
            String setId = challengeSet.getQuestionSetId();
            questions = buildChallengeQuestions(challengeSet.getQuestions(), setId);
        }
        return questions.toArray(new ChallengeQuestion[questions.size()]);
    }

    private static List<ChallengeQuestion> buildChallengeQuestions(List<ChallengeQuestionDTO> challengeSet, String setId) {
        List<ChallengeQuestion> questions = new ArrayList<>();
        for (ChallengeQuestionDTO q : challengeSet) {
            if (StringUtils.isBlank(q.getLocale())) {
                q.setLocale(LOCALE_EN_US);
            }
            questions.add(createChallenceQuestion(setId, q));
        }
        return questions;
    }

    private static ChallengeQuestion createChallenceQuestion(String setId, ChallengeQuestionDTO q) {
        return new ChallengeQuestion(WSO2_CLAIM_DIALECT + setId, q.getQuestionId(), q.getQuestion(), q
                .getLocale());
    }

    private static List<ChallengeSetDTO> buildChallengesDTO(List<ChallengeQuestion> challengeQuestions, Integer offset, Integer limit) {
//        List<ChallengeQuestion> challenges = challengeQuestions.stream()
//                .filter( distinctByKey(p -> p.getQuestionSetId()) )
//                .collect( Collectors.toList() );

        Map<String, List<ChallengeQuestion>> challengeSets = groupChallenges(challengeQuestions);

        List<ChallengeSetDTO> challengeSetDTOs = new ArrayList<>();
        for (Map.Entry<String, List<ChallengeQuestion>> entry : challengeSets.entrySet()) {
            String questionSetId = entry.getKey();
            List<ChallengeQuestion> questions = entry.getValue();
            ChallengeSetDTO challenge = getChallengeSetDTO(questionSetId, questions);
            challengeSetDTOs.add(challenge);
        }
        return challengeSetDTOs;
    }

    private static ChallengeSetDTO getChallengeSetDTO(String questionSetId, List<ChallengeQuestion> questions) {
        ChallengeSetDTO challenge = new ChallengeSetDTO();
        challenge.setQuestionSetId(questionSetId);
        List<ChallengeQuestionDTO> questionDTOs = getChallengeQuestionDTOs(questions);
        challenge.setQuestions(questionDTOs);
        return challenge;
    }

    private static ChallengeSetDTO buildChallengeDTO(List<ChallengeQuestion> challengeQuestions, String
            challengeSetId, Integer offset, Integer limit) {

        Map<String, List<ChallengeQuestion>> challengeSets = groupChallenges(challengeQuestions);

        ChallengeSetDTO challenge;
        if (challengeSets.containsKey(challengeSetId)) {
            List<ChallengeQuestion> questions = challengeSets.get(challengeSetId);
            challenge = getChallengeSetDTO(challengeSetId, questions);
        } else {
            //TODO throw correct error
            throw new WebApplicationException();
        }
        return challenge;
    }

    private static List<UserChallengeAnswer> buildChallengeAnswers(List<ChallengeAnswerDTO> challengeAnswer) {

        List<UserChallengeAnswer> answers = new ArrayList<>();
        for (ChallengeAnswerDTO ans : challengeAnswer) {
            UserChallengeAnswer answer = new UserChallengeAnswer();
            answer.setAnswer(ans.getAnswer());
            answer.setQuestion(createChallenceQuestion(ans.getQuestionSetId(), ans.getChallengeQuestion()));
            answers.add(answer);
        }
        return answers;
    }

    private static List<ChallengeAnswerDTO> buildChallengeAnswerDTOs(UserChallengeAnswer[] challengeAnswers) {

        List<ChallengeAnswerDTO> answers = new ArrayList<>();
        for (UserChallengeAnswer ans : challengeAnswers) {
            ChallengeAnswerDTO answer = new ChallengeAnswerDTO();
            ChallengeQuestionDTO questionDTO = new ChallengeQuestionDTO();
            answer.setAnswer(ans.getAnswer());
            answer.setQuestionSetId(ans.getQuestion().getQuestionSetId());
            questionDTO.setQuestion(ans.getQuestion().getQuestion());
            answer.setChallengeQuestion(questionDTO);
            answers.add(answer);
        }
        return answers;
    }


    private static List<ChallengeQuestionDTO> getChallengeQuestionDTOs(List<ChallengeQuestion> questions) {
        List<ChallengeQuestionDTO> questionDTOs = new ArrayList<>();
        for (ChallengeQuestion q : questions) {
            ChallengeQuestionDTO question = new ChallengeQuestionDTO();
            question.setLocale(q.getLocale());
            question.setQuestion(q.getQuestion());
            question.setQuestionId(q.getQuestionId());
            questionDTOs.add(question);
        }
        return questionDTOs;
    }

    private static Map<String, List<ChallengeQuestion>> groupChallenges(List<ChallengeQuestion> challengeQuestions) {
        return challengeQuestions.stream()
                .collect(groupingBy(question -> question.getQuestionSetId().split(WSO2_CLAIM_DIALECT)[1]));
    }
}
