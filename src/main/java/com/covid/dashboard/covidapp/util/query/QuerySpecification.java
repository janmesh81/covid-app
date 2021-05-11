package com.covid.dashboard.covidapp.util.query;

import com.covid.dashboard.covidapp.entity.CovidInformationEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class QuerySpecification implements Specification<CovidInformationEntity> {
    private SearchCriteria criteria;
    @Override
    public Predicate toPredicate(Root<CovidInformationEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("=")) {
            return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
           }
        return null;
    }
}
