package br.com.softplan.utils;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Getter;

import java.util.Objects;

@Getter
public class QueryDslFilter {

    private BooleanExpression mainBooleanExpression;

    public void addToMainBooleanExpression(BooleanExpression booleanExpression) {

        if (Objects.isNull(mainBooleanExpression)) {
            mainBooleanExpression = booleanExpression;
        } else {
            mainBooleanExpression = mainBooleanExpression.and(booleanExpression);
        }
    }
}
