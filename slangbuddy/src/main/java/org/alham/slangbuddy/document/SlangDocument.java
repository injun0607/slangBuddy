package org.alham.slangbuddy.document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.slangbuddy.enums.Intensity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Slf4j
@Getter
@NoArgsConstructor
public class SlangDocument {
    @Id
    private String id;

    private long userId;

    private String name;
    private String description;
    private Intensity intensity;
    private String answer;
    private boolean login;

    @Builder
    public SlangDocument(long userId, String name, String description, Intensity intensity, String answer, boolean login) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.intensity = intensity;
        this.answer = answer;
        this.login = login;
    }

    @Builder
    public SlangDocument(String name, String description, Intensity intensity, String answer, boolean login) {
        this.name = name;
        this.description = description;
        this.intensity = intensity;
        this.answer = answer;
        this.login = login;
    }


}
