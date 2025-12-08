package com.ndlcommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {NdlCommerceApplication.class, TestConfig.class})
@ActiveProfiles("test")
class NdlCommerceApplicationTests {

  @Test
  void contextLoads() {}
}
