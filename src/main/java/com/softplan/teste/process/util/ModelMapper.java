package com.softplan.teste.process.util;

import com.softplan.teste.process.model.Process;
import com.softplan.teste.process.model.User;
import com.softplan.teste.process.payload.OpinionResponse;
import com.softplan.teste.process.payload.ProcessResponse;
import com.softplan.teste.process.payload.UserSummary;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelMapper {

    public static ProcessResponse mapProcessToProcessResponse(Process process, Map<Long, Long> opinionReviewsMap, User creator, Long userReview) {
        ProcessResponse processResponse = new ProcessResponse();
        processResponse.setId(process.getId());
        processResponse.setQuestion(process.getQuestion());
        processResponse.setCreationDateTime(process.getCreatedAt());
        processResponse.setExpirationDateTime(process.getExpirationDateTime());
        Instant now = Instant.now();
        processResponse.setExpired(process.getExpirationDateTime().isBefore(now));

        List<OpinionResponse> opinionResponses = process.getOpinions().stream().map(opinion -> {
            OpinionResponse opinionResponse = new OpinionResponse();
            opinionResponse.setId(opinion.getId());
            opinionResponse.setText(opinion.getText());

            if(opinionReviewsMap.containsKey(process.getId())) {
                opinionResponse.setReviewCount(opinionReviewsMap.get(opinion.getId()));
            } else {
                opinionResponse.setReviewCount(0);
            }
            return opinionResponse;
        }).collect(Collectors.toList());

        processResponse.setOpinions(opinionResponses);
        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
        processResponse.setCreatedBy(creatorSummary);

        if(userReview != null) {
            processResponse.setSelectedOpinion(userReview);
        }

        long totalReviews = processResponse.getOpinions().stream().mapToLong(OpinionResponse::getReviewCount).sum();
        processResponse.setTotalReviews(totalReviews);

        return processResponse;
    }
}