package org.alham.slangbuddy.service.slang;

import lombok.RequiredArgsConstructor;
import org.alham.slangbuddy.document.SlangDocument;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.mapper.SlangMapper;
import org.alham.slangbuddy.repository.slang.SlangRepository;
import org.alham.slangbuddy.service.ai.AiResponseService;
import org.alham.slangbuddy.service.ai.AiResponseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlangServiceImpl implements SlangService{

    private final SlangRepository slangRepository;
    private final AiResponseService aiResponseService;
    private final SlangMapper slangMapper;

    @Override
    public SlangDTO create(SlangDTO slangDTO) {

        String aiResponse = aiResponseService.getAiResponse(slangDTO);
        slangDTO.setAnswer(aiResponse);

        SlangDocument slangDocument = slangDTO.isLogin() ?
                slangMapper.createDocumentLogin(slangDTO) : slangMapper.createDocumentNotLogin(slangDTO);

        slangRepository.save(slangDocument);
        return slangDTO;
    }

    @Override
    public List<SlangDTO> findListByUserId(Long userId) {
        List<SlangDocument> slangDocumentList = slangRepository.findListByUserId(userId);
        return slangDocumentList.stream().map(slangMapper::createDTOLogin).toList();
    }

    @Override
    public List<SlangDTO> updatePermanent(Long userId, boolean permanent ,List<String> slangIdList) {
        List<SlangDocument> slangDocumentList = slangRepository.findListByUserId(userId);


        //해당하는 항목 permanent true 로 변경
        slangDocumentList.stream()
                        .filter(slangDocument->slangIdList.contains(slangDocument.getId()))
                        .forEach(slangDocument->slangDocument.updatePermanent(permanent));

        slangRepository.saveAll(slangDocumentList);
        return slangDocumentList.stream().map(slangMapper::createDTOLogin).toList();
    }

    @Override
    public List<SlangDTO> findListByUserIdAndPermanent(Long userId, boolean permanent) {
        List<SlangDocument> slangDocumentList = slangRepository.findListByUserIdAndPermanent(userId, permanent);
        return slangDocumentList.stream().map(slangMapper::createDTOLogin).toList();
    }
}
