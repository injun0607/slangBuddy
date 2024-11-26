package org.alham.slangbuddy.service.slang;


import org.alham.slangbuddy.dto.SlangDTO;

import java.util.List;

public interface SlangService {

    public SlangDTO create(SlangDTO slangDTO);

    public List<SlangDTO> findListByUserId(Long userId);

    public List<SlangDTO> updatePermanent(Long userId, boolean permanent ,List<String> slangIdList);

    public List<SlangDTO> findListByUserIdAndPermanent(Long userId, boolean permanent);


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