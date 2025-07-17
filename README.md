# Springboot25
스프링부트 학습용
# SpringBoot25
스프링부트 학습용

=================== application.properties =========================

spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://localhost:3306/bootex
spring.datasource.username=????????
spring.datasource.password=????????


spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true



====================== build.gradle =======================

dependencies { /* 메이븐 리포지토리에서 코드를 가지고 와 코끼리를 누르면 가지고 온다 */

    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf' /*프론트 관련 */
    implementation 'org.springframework.boot:spring-boot-starter-web' /* 스프링-웹 */
    annotationProcessor 'org.projectlombok:lombok'                  /* 롬북 */
    compileOnly 'org.projectlombok:lombok'                              /* 롬북 */
    testCompileOnly 'org.projectlombok:lombok'                  /* 롬북 */
    testAnnotationProcessor 'org.projectlombok:lombok'          /* 롬북 */

    developmentOnly 'org.springframework.boot:spring-boot-devtools'  /* 부트 개발용 */
    /* 1 단계, 2 단계 설정 > src/main/resources/app.pro */
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'  /* 데이터베이스 관련 */
    runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'              /* 마리다 DB 드라이버 */


    testImplementation 'org.springframework.boot:spring-boot-starter-test' /* 테스트 J유닛 메서드 단위 테스트 */
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'          /* J유닛용 코드 */
}
