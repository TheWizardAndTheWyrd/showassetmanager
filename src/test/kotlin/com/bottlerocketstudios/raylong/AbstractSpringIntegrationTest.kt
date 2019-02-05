//package com.bottlerocketstudios.raylong
//
//import org.junit.Before
//import org.junit.ClassRule
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.context.annotation.ComponentScan
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.junit4.rules.SpringClassRule
//
//@ActiveProfiles("test")
////@AutoConfigureWireMock(port = 8666)
//@DataJpaTest
//@ComponentScan(basePackages = ["com.bottlerocketstudios.raylong"])
////@AutoConfigureMockMvc
//abstract class AbstractSpringIntegrationTest {
//
////    @Autowired
////    lateinit var cacheManager: CacheManager
//
//    companion object {
//        @ClassRule
//        @JvmField
//        val SPRING_CLASS_RULE = SpringClassRule()
//    }
//
//    @Before
//    fun resetState() {
////        cleanAllDatabases()
//        cleanAllCaches()
////        resetWiremockStatus()
//    }
//
//    fun cleanAllCaches() {
////        cacheManager.cacheNames
////                .map { cacheManager.getCache(it) }
////                .filterNotNull()
////                .forEach { it.clear() }
//    }
//}