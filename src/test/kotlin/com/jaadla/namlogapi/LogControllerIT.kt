package com.jaadla.namlogapi

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LogControllerIT {
    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext
    private lateinit var mvc: MockMvc

    @BeforeAll
    fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun `get with no username returns 404 and no body`() {
        mvc.perform(get("/api/log/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound)
                .andExpect(MockMvcResultMatchers.content().string(""))
    }
}