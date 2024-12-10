package org.alham.slangbuddy.service.slang;

import org.alham.slangbuddy.document.SlangDocument;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.enums.Intensity;
import org.alham.slangbuddy.mapper.SlangMapper;
import org.alham.slangbuddy.repository.slang.SlangRepository;
import org.alham.slangbuddy.service.ai.AiResponseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SlangServiceImplTest {
    @Mock
    private SlangRepository slangRepository;

    @Mock
    private AiResponseService aiResponseService;

    @Mock
    private SlangMapper slangMapper;

    @InjectMocks
    private SlangServiceImpl slangService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreate(){
        String userId = "testUser";
        String aiResponse = "AI response";

        SlangDTO slangDTO = new SlangDTO();
        slangDTO.setUserId(userId);
        slangDTO.setName("Test Name");
        slangDTO.setDescription("Test Description");
        slangDTO.setIntensity(Intensity.HIGH);
        slangDTO.setLogin(true);

        SlangDocument slangDocument = SlangDocument.builder()
                .userId(userId)
                .name("Test Name")
                .description("Test Description")
                .intensity(Intensity.HIGH)
                .answer(aiResponse)
                .login(true)
                .delete(false)
                .build();

        List<SlangDocument> deleteCheckList = new ArrayList<>();
        //slangDocument 5개 생성
        SlangDocument slangDocument1 = new SlangDocument();
        slangDocument1.tempSetId("1");
        slangDocument1.updateDelete(false);
        SlangDocument slangDocument2 = new SlangDocument();
        slangDocument2.tempSetId("2");
        slangDocument2.updateDelete(false);
        SlangDocument slangDocument3 = new SlangDocument();
        slangDocument3.tempSetId("3");
        slangDocument3.updateDelete(false);
        SlangDocument slangDocument4 = new SlangDocument();
        slangDocument4.tempSetId("4");
        slangDocument4.updateDelete(false);
        SlangDocument slangDocument5 = new SlangDocument();
        slangDocument5.tempSetId("5");
        slangDocument5.updateDelete(false);



        deleteCheckList.add(slangDocument1);
        deleteCheckList.add(slangDocument2);
        deleteCheckList.add(slangDocument3);
        deleteCheckList.add(slangDocument4);
        deleteCheckList.add(slangDocument5);


        when(aiResponseService.getAiResponse(slangDTO)).thenReturn(aiResponse);
        when(slangMapper.createDocumentLogin(slangDTO)).thenReturn(slangDocument);
        when(slangRepository.findListByUserIdAndDeleteOrderByCreatedDate(userId, false)).thenReturn(deleteCheckList);


        SlangDTO result = slangService.create(slangDTO);

        verify(slangRepository).save(slangDocument);
        assertEquals(aiResponse, result.getAnswer());
        assertEquals("Test Name", result.getName());
        assertEquals("Test Description", result.getDescription());
        assertEquals(Intensity.HIGH, result.getIntensity());
        assertTrue(result.isLogin());
        assertTrue(deleteCheckList.get(0).isDelete());


    }

    @Test
    void testUpdatePermanent() {
        String userId = "testUser";
        boolean permanent = true;



        List<SlangDTO> slangDTOList = new ArrayList<>();
        SlangDTO slangDTO1 = new SlangDTO();
        slangDTO1.setId("3");
        SlangDTO slangDTO2 = new SlangDTO();
        slangDTO2.setId("5");
        slangDTOList.add(slangDTO1);
        slangDTOList.add(slangDTO2);

        //총 가져오는 개수 5개
        List<SlangDocument> slangDocumentList = new ArrayList<>();
        SlangDocument slangDocument1 = new SlangDocument();
        slangDocument1.tempSetId("1");
        SlangDocument slangDocument2 = new SlangDocument();
        slangDocument2.tempSetId("2");
        SlangDocument slangDocument3 = new SlangDocument();
        slangDocument3.tempSetId("3");
        SlangDocument slangDocument4 = new SlangDocument();
        slangDocument4.tempSetId("4");
        SlangDocument slangDocument5 = new SlangDocument();
        slangDocument5.tempSetId("5");
        slangDocumentList.add(slangDocument1);
        slangDocumentList.add(slangDocument2);
        slangDocumentList.add(slangDocument3);
        slangDocumentList.add(slangDocument4);
        slangDocumentList.add(slangDocument5);

        //permanent로 저장되어있는것 2개
        List<SlangDocument> alreadySlangList = new ArrayList<>();
        alreadySlangList.add(slangDocument3);
        alreadySlangList.add(slangDocument4);

        //의도한건
        //slangDTOList에 있는것들중 3은 업데이트쿼리 안나가고 ,4 는 false로 변경 , 5는 true로 변경


        when(slangRepository.findListByUserId(userId)).thenReturn(slangDocumentList);
        when(slangRepository.findListByUserIdAndPermanent(userId, true)).thenReturn(alreadySlangList);

        List<SlangDTO> result = slangService.updatePermanent(userId, permanent, slangDTOList);




        verify(slangRepository, times(2)).saveAll(any());
//        verify(slangRepository,times(2)).save(any());
        assertEquals(5, result.size());


        //테스트케이스 확인해야할것


    }
}