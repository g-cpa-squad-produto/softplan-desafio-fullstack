package com.softplan.processmanagerapi.factory;

import com.softplan.processmanagerapi.models.Opinion;
import com.softplan.processmanagerapi.payload.request.OpinionRequest;
import com.softplan.processmanagerapi.payload.response.OpinionResponse;
import org.springframework.stereotype.Component;

@Component
public class OpinionFactory {

    public Opinion getEntity(OpinionRequest opinionResponse){
        Opinion opinion = new Opinion();
        opinion.setOpinion(opinionResponse.getOpinion());
        return opinion;
    }

    public OpinionResponse getOpinionResponse(Opinion opinion) {
        OpinionResponse opinionResponse = new OpinionResponse();
        opinionResponse.setId(opinion.getId());
        opinionResponse.setOpinion(opinion.getOpinion());
        return opinionResponse;
    }
}
