package com.ssafy.moamoa.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.moamoa.domain.dto.ProjectForm;
import com.ssafy.moamoa.domain.entity.Area;
import com.ssafy.moamoa.domain.entity.Category;
import com.ssafy.moamoa.domain.entity.Site;
import com.ssafy.moamoa.domain.entity.TechStack;
import com.ssafy.moamoa.domain.entity.TechStackCategory;
import com.ssafy.moamoa.repository.AreaRepository;
import com.ssafy.moamoa.repository.CategoryRepository;
import com.ssafy.moamoa.repository.SiteRepository;
import com.ssafy.moamoa.repository.TechStackCategoryRepository;
import com.ssafy.moamoa.repository.TechStackRepository;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class InitService {
    private final CategoryRepository categoryRepository;
    private final TechStackRepository techStackRepository;
    private final AreaRepository areaRepository;
    private final TechStackCategoryRepository techStackCategoryRepository;
    private final SiteRepository siteRepository;

    //==배포시 삭제==//
    private final UserService userService;
    private final ProjectService projectService;

    public void addTechstackCatagory() {
        //==Category==//
        Category categoryBack = new Category("BackEnd");
        categoryRepository.save(categoryBack);

        Category categoryFront = new Category("FrontEnd");
        categoryRepository.save(categoryFront);

        Category categoryMobile = new Category("Mobile");
        categoryRepository.save(categoryMobile);

        Category categoryEtc = new Category("Etc");
        categoryRepository.save(categoryEtc);

        //==Techstack==//
        // BackEnd
        TechStack techStackC = new TechStack("C", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/c.png");
        techStackRepository.save(techStackC);

        TechStack techStackCsharp = new TechStack("C#", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/csharp.png");
        techStackRepository.save(techStackCsharp);

        TechStack techStackCpp = new TechStack("C++", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/cpp.png");
        techStackRepository.save(techStackCpp);

        TechStack techStackJava = new TechStack("Java", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/java.png");
        techStackRepository.save(techStackJava);

        TechStack techStackPython = new TechStack("Python", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/python.png");
        techStackRepository.save(techStackPython);

        TechStack techStackKotlin = new TechStack("Kotlin", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/kotlin.png");
        techStackRepository.save(techStackKotlin);

        TechStack techStackGo = new TechStack("Go", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/go.png");
        techStackRepository.save(techStackGo);

        TechStack techStackNestjs = new TechStack("Nest.js", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/nestjs.png");
        techStackRepository.save(techStackNestjs);

        TechStack techStackNodejs = new TechStack("Node.js", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/nodejs.png");
        techStackRepository.save(techStackNodejs);

        TechStack techStackDjango = new TechStack("Django", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/django.png");
        techStackRepository.save(techStackDjango);

        TechStack techStackSpring = new TechStack("Spring", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/spring.png");
        techStackRepository.save(techStackSpring);

        TechStack techStackMysql = new TechStack("MySQL", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/mysql.png");
        techStackRepository.save(techStackMysql);

        TechStack techStackGraphQL = new TechStack("GraphQL", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/graphql.png");
        techStackRepository.save(techStackGraphQL);

        TechStack techStackRedis = new TechStack("Redis", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/redis.png");
        techStackRepository.save(techStackRedis);

        TechStack techStackMongodb = new TechStack("MongoDB", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/mongodb.png");
        techStackRepository.save(techStackMongodb);

        TechStack techStackFirebase = new TechStack("Firebase", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/firebase.png");
        techStackRepository.save(techStackFirebase);

        // FrontEnd
        TechStack techStackJs = new TechStack("Javascript", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/javascript.png");
        techStackRepository.save(techStackJs);

        TechStack techStackTs = new TechStack("Typescript", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/typescript.png");
        techStackRepository.save(techStackTs);

        TechStack techStackReact = new TechStack("React", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/react.png");
        techStackRepository.save(techStackReact);

        TechStack techStackVue = new TechStack("Vue", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/vue.png");
        techStackRepository.save(techStackVue);

        TechStack techStackAngular = new TechStack("Angular", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/angular.png");
        techStackRepository.save(techStackAngular);

        TechStack techStackSvelte = new TechStack("Svelte", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/svlete.png");
        techStackRepository.save(techStackSvelte);

        TechStack techStackNextjs = new TechStack("Next.js", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/nextjs.png");
        techStackRepository.save(techStackNextjs);

        // Mobile
        TechStack techStackSwift = new TechStack("Swift", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/swift.png");
        techStackRepository.save(techStackSwift);

        TechStack techStackSwiftui = new TechStack("SwiftUI", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/swiftui.png");
        techStackRepository.save(techStackSwiftui);

        TechStack techStackFlutter = new TechStack("Flutter", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/flutter.png");
        techStackRepository.save(techStackFlutter);

        // Etc
        TechStack techStackAws = new TechStack("AWS", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/aws.png");
        techStackRepository.save(techStackAws);

        TechStack techStackDocker = new TechStack("Docker", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/docker.png");
        techStackRepository.save(techStackDocker);

        TechStack techStackKubernetes = new TechStack("Kubernetes", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/kubernetes.png");
        techStackRepository.save(techStackKubernetes);

        TechStack techStackJenkins = new TechStack("Jenkins", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/jenkins.png");
        techStackRepository.save(techStackJenkins);

        TechStack techStackFigma = new TechStack("Figma", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/figma.png");
        techStackRepository.save(techStackFigma);

        TechStack techStackGit = new TechStack("Git", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/git.png");
        techStackRepository.save(techStackGit);

        TechStack techStackUnity = new TechStack("Unity", "https://s3.ap-northeast-2.amazonaws.com/ssafy.moamoa.image.bucket/techstacks/unity.png");
        techStackRepository.save(techStackUnity);

        //==TechstackCategory==//
        // BackEnd
        TechStackCategory techStackCategoryB1 = new TechStackCategory();
        techStackCategoryB1.setCategory(categoryBack);
        techStackCategoryB1.setTechstack(techStackC);
        techStackCategoryRepository.save(techStackCategoryB1);

        TechStackCategory techStackCategoryB2 = new TechStackCategory();
        techStackCategoryB2.setCategory(categoryBack);
        techStackCategoryB2.setTechstack(techStackCsharp);
        techStackCategoryRepository.save(techStackCategoryB2);

        TechStackCategory techStackCategoryB3 = new TechStackCategory();
        techStackCategoryB3.setCategory(categoryBack);
        techStackCategoryB3.setTechstack(techStackCpp);
        techStackCategoryRepository.save(techStackCategoryB3);

        TechStackCategory techStackCategoryB4 = new TechStackCategory();
        techStackCategoryB4.setCategory(categoryBack);
        techStackCategoryB4.setTechstack(techStackJava);
        techStackCategoryRepository.save(techStackCategoryB4);

        TechStackCategory techStackCategoryB5 = new TechStackCategory();
        techStackCategoryB5.setCategory(categoryBack);
        techStackCategoryB5.setTechstack(techStackPython);
        techStackCategoryRepository.save(techStackCategoryB5);

        TechStackCategory techStackCategoryB6 = new TechStackCategory();
        techStackCategoryB6.setCategory(categoryBack);
        techStackCategoryB6.setTechstack(techStackKotlin);
        techStackCategoryRepository.save(techStackCategoryB6);

        TechStackCategory techStackCategoryB7 = new TechStackCategory();
        techStackCategoryB7.setCategory(categoryBack);
        techStackCategoryB7.setTechstack(techStackGo);
        techStackCategoryRepository.save(techStackCategoryB7);

        TechStackCategory techStackCategoryB8 = new TechStackCategory();
        techStackCategoryB8.setCategory(categoryBack);
        techStackCategoryB8.setTechstack(techStackNestjs);
        techStackCategoryRepository.save(techStackCategoryB8);

        TechStackCategory techStackCategoryB9 = new TechStackCategory();
        techStackCategoryB9.setCategory(categoryBack);
        techStackCategoryB9.setTechstack(techStackNodejs);
        techStackCategoryRepository.save(techStackCategoryB9);

        TechStackCategory techStackCategoryB10 = new TechStackCategory();
        techStackCategoryB10.setCategory(categoryBack);
        techStackCategoryB10.setTechstack(techStackDjango);
        techStackCategoryRepository.save(techStackCategoryB10);

        TechStackCategory techStackCategoryB11 = new TechStackCategory();
        techStackCategoryB11.setCategory(categoryBack);
        techStackCategoryB11.setTechstack(techStackSpring);
        techStackCategoryRepository.save(techStackCategoryB11);

        TechStackCategory techStackCategoryB12 = new TechStackCategory();
        techStackCategoryB12.setCategory(categoryBack);
        techStackCategoryB12.setTechstack(techStackMysql);
        techStackCategoryRepository.save(techStackCategoryB12);

        TechStackCategory techStackCategoryB13 = new TechStackCategory();
        techStackCategoryB13.setCategory(categoryBack);
        techStackCategoryB13.setTechstack(techStackGraphQL);
        techStackCategoryRepository.save(techStackCategoryB13);

        TechStackCategory techStackCategoryB14 = new TechStackCategory();
        techStackCategoryB14.setCategory(categoryBack);
        techStackCategoryB14.setTechstack(techStackRedis);
        techStackCategoryRepository.save(techStackCategoryB14);

        TechStackCategory techStackCategoryB15 = new TechStackCategory();
        techStackCategoryB15.setCategory(categoryBack);
        techStackCategoryB15.setTechstack(techStackMongodb);
        techStackCategoryRepository.save(techStackCategoryB15);

        TechStackCategory techStackCategoryB16 = new TechStackCategory();
        techStackCategoryB16.setCategory(categoryBack);
        techStackCategoryB16.setTechstack(techStackFirebase);
        techStackCategoryRepository.save(techStackCategoryB16);

        // FrontEnd
        TechStackCategory techStackCategoryF1 = new TechStackCategory();
        techStackCategoryF1.setCategory(categoryFront);
        techStackCategoryF1.setTechstack(techStackJs);

        techStackCategoryRepository.save(techStackCategoryF1);

        TechStackCategory techStackCategoryF2 = new TechStackCategory();
        techStackCategoryF2.setCategory(categoryFront);
        techStackCategoryF2.setTechstack(techStackTs);
        techStackCategoryRepository.save(techStackCategoryF2);

        TechStackCategory techStackCategoryF3 = new TechStackCategory();
        techStackCategoryF3.setCategory(categoryFront);
        techStackCategoryF3.setTechstack(techStackReact);
        techStackCategoryRepository.save(techStackCategoryF3);

        TechStackCategory techStackCategoryF4 = new TechStackCategory();
        techStackCategoryF4.setCategory(categoryFront);
        techStackCategoryF4.setTechstack(techStackVue);
        techStackCategoryRepository.save(techStackCategoryF4);

        TechStackCategory techStackCategoryF5 = new TechStackCategory();
        techStackCategoryF5.setCategory(categoryFront);
        techStackCategoryF5.setTechstack(techStackAngular);
        techStackCategoryRepository.save(techStackCategoryF5);

        TechStackCategory techStackCategoryF6 = new TechStackCategory();
        techStackCategoryF6.setCategory(categoryFront);
        techStackCategoryF6.setTechstack(techStackSvelte);
        techStackCategoryRepository.save(techStackCategoryF6);

        TechStackCategory techStackCategoryF7 = new TechStackCategory();
        techStackCategoryF7.setCategory(categoryFront);
        techStackCategoryF7.setTechstack(techStackNextjs);
        techStackCategoryRepository.save(techStackCategoryF7);

        // Mobile
        TechStackCategory techStackCategoryM1 = new TechStackCategory();
        techStackCategoryM1.setCategory(categoryMobile);
        techStackCategoryM1.setTechstack(techStackSwift);
        techStackCategoryRepository.save(techStackCategoryM1);

        TechStackCategory techStackCategoryM2 = new TechStackCategory();
        techStackCategoryM2.setCategory(categoryMobile);
        techStackCategoryM2.setTechstack(techStackSwiftui);
        techStackCategoryRepository.save(techStackCategoryM2);

        TechStackCategory techStackCategoryM3 = new TechStackCategory();
        techStackCategoryM3.setCategory(categoryMobile);
        techStackCategoryM3.setTechstack(techStackKotlin);
        techStackCategoryRepository.save(techStackCategoryM3);

        TechStackCategory techStackCategoryM4 = new TechStackCategory();
        techStackCategoryM4.setCategory(categoryMobile);
        techStackCategoryM4.setTechstack(techStackReact);
        techStackCategoryRepository.save(techStackCategoryM4);

        TechStackCategory techStackCategoryM5 = new TechStackCategory();
        techStackCategoryM5.setCategory(categoryMobile);
        techStackCategoryM5.setTechstack(techStackFlutter);
        techStackCategoryRepository.save(techStackCategoryM5);

        // Etc
        TechStackCategory techStackCategoryE1 = new TechStackCategory();
        techStackCategoryE1.setCategory(categoryEtc);
        techStackCategoryE1.setTechstack(techStackAws);
        techStackCategoryRepository.save(techStackCategoryE1);

        TechStackCategory techStackCategoryE2 = new TechStackCategory();
        techStackCategoryE2.setCategory(categoryEtc);
        techStackCategoryE2.setTechstack(techStackDocker);
        techStackCategoryRepository.save(techStackCategoryE2);

        TechStackCategory techStackCategoryE3 = new TechStackCategory();
        techStackCategoryE3.setCategory(categoryEtc);
        techStackCategoryE3.setTechstack(techStackKubernetes);
        techStackCategoryRepository.save(techStackCategoryE3);

        TechStackCategory techStackCategoryE4 = new TechStackCategory();
        techStackCategoryE4.setCategory(categoryEtc);
        techStackCategoryE4.setTechstack(techStackJenkins);
        techStackCategoryRepository.save(techStackCategoryE4);

        TechStackCategory techStackCategoryE5 = new TechStackCategory();
        techStackCategoryE5.setCategory(categoryEtc);
        techStackCategoryE5.setTechstack(techStackFigma);
        techStackCategoryRepository.save(techStackCategoryE5);

        TechStackCategory techStackCategoryE6 = new TechStackCategory();
        techStackCategoryE6.setCategory(categoryEtc);
        techStackCategoryE6.setTechstack(techStackGit);
        techStackCategoryRepository.save(techStackCategoryE6);

        TechStackCategory techStackCategoryE7 = new TechStackCategory();
        techStackCategoryE7.setCategory(categoryEtc);
        techStackCategoryE7.setTechstack(techStackUnity);
        techStackCategoryRepository.save(techStackCategoryE7);

    }

    public void addArea() {
        Area areaSeoul = new Area("서울특별시");
        areaRepository.save(areaSeoul);

        Area areaBusan = new Area("부산광역시");
        areaRepository.save(areaBusan);

        Area areaDaegu = new Area("대구광역시");
        areaRepository.save(areaDaegu);

        Area areaIncheon = new Area("인천광역시");
        areaRepository.save(areaIncheon);

        Area areaGwangju = new Area("광주광역시");
        areaRepository.save(areaGwangju);

        Area areaDaejeon = new Area("대전광역시");
        areaRepository.save(areaDaejeon);

        Area areaUlsan = new Area("울산광역시");
        areaRepository.save(areaUlsan);

        Area areaSejong = new Area("세종특별자치시");
        areaRepository.save(areaSejong);

        Area areaGyeonggi = new Area("경기도");
        areaRepository.save(areaGyeonggi);

        Area areaGangwon = new Area("강원도");
        areaRepository.save(areaGangwon);

        Area areaChungcheongbuk = new Area("충청북도");
        areaRepository.save(areaChungcheongbuk);

        Area areaChungcheongnam = new Area("충청남도");
        areaRepository.save(areaChungcheongnam);

        Area areaJeollabuk = new Area("전라북도");
        areaRepository.save(areaJeollabuk);

        Area areaJeollanam = new Area("전라남도");
        areaRepository.save(areaJeollanam);

        Area areaGyeongsangbuk = new Area("경상북도");
        areaRepository.save(areaGyeongsangbuk);

        Area areaGyeongsangnam = new Area("경상남도");
        areaRepository.save(areaGyeongsangnam);

        Area areaJeju = new Area("제주특별자치시");
        areaRepository.save(areaJeju);
    }

    public void addUser() {
        userService.signup("ssafy@ssafy.com", "s@12341234", "김싸피");
        userService.signup("yeonjin@ssafy.com", "s@12341234", "정연진");
        userService.signup("youjiyeon4@ssafy.com", "s@12341234", "유지연");
        userService.signup("smartpodo@ssafy.com", "s@12341234", "김동동");
        userService.signup("ssafy1@ssafy.com", "s@12341234", "김싸피1");
        userService.signup("ssafy2@ssafy.com", "s@12341234", "김싸피2");
        userService.signup("ssafy3@ssafy.com", "s@12341234", "김싸피3");
        userService.signup("ssafy4@ssafy.com", "s@12341234", "김싸피4");
        userService.signup("ssafy5@ssafy.com", "s@12341234", "김싸피5");
        userService.signup("ssafy6@ssafy.com", "s@12341234", "김싸피6");
        userService.signup("ssafy7@ssafy.com", "s@12341234", "김싸피7");
        userService.signup("ssafy8@ssafy.com", "s@12341234", "김싸피8");
        userService.signup("ssafy9@ssafy.com", "s@12341234", "김싸피9");
        userService.signup("ssafy10@ssafy.com", "s@12341234", "김싸피10");
        userService.signup("ssafy11@ssafy.com", "s@12341234", "김싸피11");
        userService.signup("ssafy12@ssafy.com", "s@12341234", "김싸피12");
        userService.signup("ssafy13@ssafy.com", "s@12341234", "김싸피13");
        userService.signup("ssafy14@ssafy.com", "s@12341234", "김싸피14");
        userService.signup("ssafy15@ssafy.com", "s@12341234", "김싸피15");
        userService.signup("ssafy16@ssafy.com", "s@12341234", "김싸피16");
        userService.signup("ssafy17@ssafy.com", "s@12341234", "김싸피17");
        userService.signup("ssafy18@ssafy.com", "s@12341234", "김싸피18");
        userService.signup("ssafy19@ssafy.com", "s@12341234", "김싸피19");

    }

    public void addProject() throws Exception {
        ProjectForm studyOffForm = new ProjectForm(0L, "studyOn", "", "", "OFFLINE", 2, "STUDY", "2023-02-21", 1L,
                new Long[]{5L, 6L}, 1L);
        ProjectForm studyOnForm = new ProjectForm(0L, "studyOff", "", "", "ONLINE", 2, "STUDY", "2023-02-21", 1L,
                new Long[]{5L, 6L}, 1L);
        ProjectForm projectOffForm = new ProjectForm(0L, "projectOff", "", "", "OFFLINE", 10, "PROJECT", "2023-02-21",
                1L, new Long[]{5L, 6L}, 1L);
        ProjectForm projectOnForm = new ProjectForm(0L, "projectOn", "", "", "ONLINE", 10, "PROJECT", "2023-02-21", 1L,
                new Long[]{5L, 6L}, 1L);
        for (int i = 0; i < 20; i++) {
            projectService.creatProject(studyOffForm,null);
            projectService.creatProject(projectOffForm,null);
            projectService.creatProject(studyOnForm,null);
            projectService.creatProject(projectOnForm,null);

        }

    }

    public void addSite() {
        Site github = Site.builder().name("Github").build();
        Site velog = Site.builder().name("Velog").build();
        Site tistory = Site.builder().name("Tistory").build();
        Site cutsom = Site.builder().name("Custom").build();

        siteRepository.save(github);
        siteRepository.save(velog);
        siteRepository.save(tistory);
        siteRepository.save(cutsom);

    }

}
