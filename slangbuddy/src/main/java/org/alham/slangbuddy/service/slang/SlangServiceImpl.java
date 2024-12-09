package org.alham.slangbuddy.service.slang;

import lombok.RequiredArgsConstructor;
import org.alham.slangbuddy.document.SlangDocument;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.enums.Template;
import org.alham.slangbuddy.enums.UserRole;
import org.alham.slangbuddy.mapper.SlangMapper;
import org.alham.slangbuddy.repository.slang.SlangRepository;
import org.alham.slangbuddy.service.ai.AiResponseService;
import org.alham.slangbuddy.service.ai.AiResponseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlangServiceImpl implements SlangService{

    private final SlangRepository slangRepository;
    private final AiResponseService aiResponseService;
    private final SlangMapper slangMapper;

    @Override
    public SlangDTO create(SlangDTO slangDTO) {

        //UserRole 임시
        UserRole userRole = UserRole.BASIC;

        String aiResponse = aiResponseService.getAiResponse(slangDTO);
        slangDTO.setAnswer(aiResponse);

        SlangDocument slangDocument = slangDTO.isLogin() ?
                slangMapper.createDocumentLogin(slangDTO) : slangMapper.createDocumentNotLogin(slangDTO);


        // 기본적으로 들어갈때 delete 로 들어간다 -> delete는 최대 맥스개수가 있고 그것을 넘어가면 오래된것부터 삭제한다.
        // permanent라는것이 있는데 , 이것은 사용자가 해당 대화를 영구적으로 기억하겠다는 뜻
        // 만약 permanent로 저장되어있는개수가 max 개수랑 같다면 계속 삭제한다.

        //deleteCheckList는 delete가 false인것들만 가져온다.
        List<SlangDocument> deleteCheckList = slangRepository.findListByUserIdAndDeleteOrderByCreatedDate(slangDTO.getUserId(), false);
        if(deleteCheckList.size() >= userRole.getMaxSaved()){
            //맨처음에 해당하는걸 지운다
            deleteCheckList.stream().findFirst().ifPresent(slangDoc -> {
                slangDoc.updateDelete(true);
                slangRepository.save(slangDoc);
            });
        }

        slangRepository.save(slangDocument);

        return slangDTO;
    }

    @Override
    public List<SlangDTO> findListByUserId(String userId) {
        List<SlangDocument> slangDocumentList = slangRepository.findListByUserId(userId);
        return slangDocumentList.stream().map(slangMapper::createDTOLogin).toList();
    }

    //
    @Override
    public List<SlangDTO> updatePermanent(String userId, boolean permanent ,List<SlangDTO> slangDTOList) {

        List<SlangDocument> slangDocumentList = slangRepository.findListByUserId(userId);

        List<String> slangIdList = slangDTOList.stream().map(SlangDTO::getId).toList();

        //기존 slangList 가져오기
        List<SlangDocument> alreadySlangList = slangRepository.findListByUserIdAndPermanent(userId, true);
        List<SlangDocument> updateOppositeList = new ArrayList<>();

        //리스트 확인 후 -> 해당하는 항목이 없으면 permanent false 로 변경
        alreadySlangList.forEach(slangDocument->{
            if(!slangIdList.contains(slangDocument.getId())){
                slangDocument.updatePermanent(!permanent);
                updateOppositeList.add(slangDocument);
            }
        });

        List<String> alreadySlangIdList = alreadySlangList.stream().map(SlangDocument::getId).toList();

        //해당하는 항목 permanent true 로 변경
        List<SlangDocument> saveList = slangDocumentList.stream()
                .filter(slangDocument -> slangIdList.contains(slangDocument.getId()) && !alreadySlangIdList.contains(slangDocument.getId()))
                .peek(slangDocument -> slangDocument.updatePermanent(permanent)).toList();

        slangRepository.saveAll(saveList);
        slangRepository.saveAll(updateOppositeList);
        return slangDocumentList.stream().map(slangMapper::createDTOLogin).toList();
    }

    @Override
    public List<SlangDTO> findListByUserIdAndPermanent(String userId, boolean permanent) {
        List<SlangDocument> slangDocumentList = slangRepository.findListByUserIdAndPermanent(userId, permanent);
        return slangDocumentList.stream().map(slangMapper::createDTOLogin).toList();
    }

    @Override
    public List<SlangDTO> findListByUserIdAndPermanentAndTemplate(String userId, boolean permanent, Template template) {
        List<SlangDocument> slangDocumentList = slangRepository.findListByUserIdAndPermanentAndTemplate(userId, permanent, template);
        return slangDocumentList.stream().map(slangMapper::createDTOLogin).toList();
    }

}
