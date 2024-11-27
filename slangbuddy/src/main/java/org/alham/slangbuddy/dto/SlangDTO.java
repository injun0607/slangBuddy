package org.alham.slangbuddy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alham.slangbuddy.enums.Intensity;
import org.alham.slangbuddy.enums.Template;

@Getter
@Setter
@NoArgsConstructor
public class SlangDTO {

    private String id;
    private long userId;
    private String name;
    private String description;
    private Intensity intensity;
    private Template template;
    private String answer;
    private boolean login;

    @Builder
    public SlangDTO(String id,long userId, String name, String description, String answer,Intensity intensity,Template template,boolean login) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.intensity = intensity;
        this.template = template;
        this.answer = answer;
        this.login = login;
    }

    @Builder
    public SlangDTO(String name,String description, String answer,Intensity intensity ,Template template ,boolean login) {
        this.name = name;
        this.description = description;
        this.intensity = intensity;
        this.template = template;
        this.answer = answer;
        this.login = login;
    }

}


