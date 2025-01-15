import React from "react";
import styles from "./Login.module.css"; // CSS 파일 분리

const LoginScreen = () => {
  const handleLogin = () => {
    // OAuth 2.0 로그인 로직 (예: Google, Facebook 등 리디렉션)
    // window.location.href = "https://naver.com";
    // oauth2.0으로 넘겨버리고 


  };

  return (
    <div className={styles.loginContainer}>
    <div className={styles.loginBox}>
      <h1 className={styles.loginTitle}>Welcome Back!</h1>
      <p className={styles.loginSubtitle}>따듯한 위로를 드리는 곳에 오신 걸 환영합니다</p>
      <div className={styles.loginButtons}>
        <button className={`${styles.loginButton} ${styles.apple}`} onClick={() => handleLogin("apple")}>
          Apple 로그인
        </button>
        <button className={`${styles.loginButton} ${styles.naver}`} onClick={() => handleLogin("naver")}>
          네이버 로그인
        </button>
        <button className={`${styles.loginButton} ${styles.kakao}`} onClick={() => handleLogin("kakao")}>
          카카오 로그인
        </button>
        <button className={`${styles.loginButton} ${styles.google}`} onClick={() => handleLogin("google")}>
          Google 로그인
        </button>
      </div>
    </div>
  </div>
  );
};

export default LoginScreen;