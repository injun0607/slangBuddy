package org.alham.slangbuddy.service.slang;

import org.alham.slangbuddy.document.SlangDocument;
import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.mapper.SlangMapper;
import org.alham.slangbuddy.repository.slang.SlangRepository;
import org.alham.slangbuddy.service.ai.AiResponseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void testUpdatePermanent() {
        String userId = "testUser";
        boolean permanent = true;

        List<SlangDTO> slangDTOList = new ArrayList<>();
        SlangDTO slangDTO1 = new SlangDTO();
        slangDTO1.setId("1");
        SlangDTO slangDTO2 = new SlangDTO();
        slangDTO2.setId("2");
        slangDTOList.add(slangDTO1);
        slangDTOList.add(slangDTO2);

        List<SlangDocument> slangDocumentList = new ArrayList<>();
        SlangDocument slangDocument1 = new SlangDocument();
        slangDocument1.tempSetId("1");
        SlangDocument slangDocument2 = new SlangDocument();
        slangDocument2.tempSetId("2");
        slangDocumentList.add(slangDocument1);
        slangDocumentList.add(slangDocument2);

        List<SlangDocument> alreadySlangList = new ArrayList<>();
        SlangDocument slangDocument3 = new SlangDocument();
        slangDocument3.tempSetId("3");
        alreadySlangList.add(slangDocument3);

        when(slangRepository.findListByUserId(userId)).thenReturn(slangDocumentList);
        when(slangRepository.findListByUserIdAndPermanent(userId, true)).thenReturn(alreadySlangList);

        List<SlangDTO> result = slangService.updatePermanent(userId, permanent, slangDTOList);

        verify(slangRepository, times(2)).saveAll(any());
        assertEquals(2, result.size());


        //테스트케이스 확인해야할것


    }
}