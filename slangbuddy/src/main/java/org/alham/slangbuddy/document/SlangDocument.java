package org.alham.slangbuddy.document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.slangbuddy.enums.Intensity;
import org.alham.slangbuddy.enums.Template;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@NoArgsConstructor
public class SlangDocument {
    @Id
    private String id;

    private String userId;

    private String name;
    private String description;
    private Intensity intensity;
    private String answer;
    private Template template;
    private boolean login;
    //고정 시킬지 여부
    private boolean permanent;
    //기억 할지 여부
    private boolean delete;
    private LocalDateTime createdDate = LocalDateTime.now();


    @Builder
    public SlangDocument(String userId, String name, String description, Intensity intensity, String answer,Template template, boolean login, boolean delete) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.intensity = intensity;
        this.answer = answer;
        this.template = template;
        this.login = login;
        this.delete = delete;
    }

    @Builder
    public SlangDocument(String name, String description, Intensity intensity, String answer, boolean login) {
        this.name = name;
        this.description = description;
        this.intensity = intensity;
        this.answer = answer;
        this.login = login;
    }

    public void updatePermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public void updateDelete(boolean delete) {
        this.delete = delete;
    }

    public void tempSetId(String id){
        this.id = id;
    }


}
