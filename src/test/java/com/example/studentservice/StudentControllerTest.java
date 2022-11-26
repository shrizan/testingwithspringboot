package com.example.studentservice;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getStudent_forSavedStudent_isReturned() throws Exception {
        // given
        BDDMockito.given(studentService.getStudentById(1)).willReturn(Student.builder().id(1).name("Mark").build());
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
                .andExpect((status().isOk()))
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value("Mark"));
    }
}
