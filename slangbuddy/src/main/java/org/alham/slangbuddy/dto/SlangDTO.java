package org.alham.slangbuddy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alham.slangbuddy.enums.Intensity;

@Getter
@Setter
@NoArgsConstructor
public class SlangDTO {

    private String id;
    private long userId;
    private String name;
    private String description;
    private Intensity intensity;
    private String answer;
    private boolean login;

    @Builder
    public SlangDTO(long userId, String name, String description, String answer,Intensity intensity, boolean login) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.intensity = intensity;
        this.answer = answer;
        this.login = login;
    }

    @Builder
    public SlangDTO(String name,String description, String answer,Intensity intensity ,boolean login) {
        this.name = name;
        this.description = description;
        this.intensity = intensity;
        this.answer = answer;
        this.login = login;
    }

}


