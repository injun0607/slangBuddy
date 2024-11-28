package org.alham.slangbuddy.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alham.slangbuddy.enums.UserLoginType;
import org.alham.slangbuddy.enums.UserRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@NoArgsConstructor
public class UserDocument {
    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String userId;

    private int age;
    private UserLoginType loginType;
    private UserRole userRole;


    private List<String> features = new ArrayList<>();

}
