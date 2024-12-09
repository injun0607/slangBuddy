package org.alham.slangbuddy.repository.slang;

import org.alham.slangbuddy.document.SlangDocument;
import org.alham.slangbuddy.enums.Template;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SlangRepository extends MongoRepository<SlangDocument, String>{

    public List<SlangDocument> findListByUserId(String userId);

    public List<SlangDocument> findListByUserIdAndPermanent(String userId, boolean permanent);

    public List<SlangDocument> findListByUserIdAndPermanentAndTemplate(String userId, boolean permanent, Template template);

    public List<SlangDocument> findListByUserIdAndDeleteOrderByCreatedDate(String userId, boolean delete);

}
