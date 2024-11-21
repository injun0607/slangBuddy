package org.alham.slangbuddy.service.slang;

import lombok.RequiredArgsConstructor;
import org.alham.slangbuddy.document.SlangDocument;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.mapper.SlangMapper;
import org.alham.slangbuddy.repository.slang.SlangRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlangServiceImpl implements SlangService{

    private final SlangRepository slangRepository;
    private final SlangMapper slangMapper;

    @Override
    public SlangDTO create(SlangDTO slangDTO) {

        SlangDocument slangDocument = slangDTO.isLogin() ?
                slangMapper.createDocumentLogin(slangDTO) : slangMapper.createDocumentNotLogin(slangDTO);

        slangRepository.save(slangDocument);

        return null;
    }
}
