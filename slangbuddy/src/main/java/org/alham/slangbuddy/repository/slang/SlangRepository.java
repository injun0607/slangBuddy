package org.alham.slangbuddy.repository.slang;

import org.alham.slangbuddy.document.SlangDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SlangRepository extends MongoRepository<SlangDocument, String>{

    public List<SlangDocument> findListByUserId(Long userId);

    public List<SlangDocument> findListByUserIdAndPermanent(Long userId, boolean permanent);

}
