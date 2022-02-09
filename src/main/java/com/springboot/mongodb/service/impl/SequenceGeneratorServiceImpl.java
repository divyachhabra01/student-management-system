package com.springboot.mongodb.service.impl;

import com.springboot.mongodb.model.DbSequence;
import com.springboot.mongodb.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
@Service
public class SequenceGeneratorServiceImpl implements SequenceGeneratorService {
    @Autowired
    private MongoOperations mongoOperations;
    @Override
    public int generateSequenceNumber(String sequenceName) {
        // get sequence no
        Query query=new Query(Criteria.where("id").is(sequenceName));
        // update the sequence
        Update update=new Update().inc("seq",1);
        // modify the document
        DbSequence counter=mongoOperations.findAndModify(query,update,options().returnNew(true).upsert(true),DbSequence.class );
        return !Objects.isNull(counter)? counter.getSeq():1;
    }
}
