package org.alham.slangbuddy.repository.slang;

import org.alham.slangbuddy.document.SlangDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SlangRepository extends MongoRepository<SlangDocument, String>{
}
