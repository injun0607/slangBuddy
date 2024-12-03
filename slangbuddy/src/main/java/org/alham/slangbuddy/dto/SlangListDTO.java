package org.alham.slangbuddy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SlangListDTO {


    private String userId;
    private boolean permanent;
    List<SlangDTO> slangDTOList = new ArrayList<>();

}
