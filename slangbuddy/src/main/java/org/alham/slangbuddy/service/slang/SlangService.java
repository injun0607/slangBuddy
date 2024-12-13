package org.alham.slangbuddy.service.slang;


import org.alham.slangbuddy.dto.SlangDTO;
import org.alham.slangbuddy.enums.Template;

import java.util.List;

public interface SlangService {

    public SlangDTO create(SlangDTO slangDTO);

    public SlangDTO createNotLogin(SlangDTO slangDTO);

    public List<SlangDTO> findListByUserId(String userId);

    public List<SlangDTO> updatePermanent(String userId, boolean permanent ,List<SlangDTO> slangDtoList);

    public List<SlangDTO> findListByUserIdAndPermanent(String userId, boolean permanent);

    public List<SlangDTO> findListByUserIdAndPermanentAndTemplate(String userId, boolean permanent, Template template);

    public List<SlangDTO> findListByUserIdAndDeleteOrderByCreatedDate(String userId, boolean delete, Template template);


}
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/oauth2/**").permitAll()
//                                .anyRequest().authenticated()
//                ).sessionManagement(sessionManagement ->
//                        sessionManagement
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                ).csrf(AbstractHttpConfigurer::disable);
//        return http.build();
//    }