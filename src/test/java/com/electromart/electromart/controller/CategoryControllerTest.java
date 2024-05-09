package com.electromart.electromart.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.electromart.electromart.ElectromartApplication;
import com.electromart.electromart.dto.CategoryDTO;
import com.electromart.electromart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ElectromartApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:applicationtest.properties")
public class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void testFetchAllCategories() throws Exception {
        List<CategoryDTO> categoryList = List.of(new CategoryDTO(1L, "Electronics", "All electronic items"));
        when(categoryService.getAllCategories()).thenReturn(categoryList);

        mvc.perform(get("/category")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Electronics")))
                .andExpect(jsonPath("$[0].description", is("All electronic items")));
    }

    @Test
    public void testFetchCategoryById() throws Exception {
        CategoryDTO categoryDTO = (new CategoryDTO(1L, "Electronics", "All electronic items"));
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(categoryDTO));

        mvc.perform(get("/category/category_id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Electronics")))
                .andExpect(jsonPath("$.description", is("All electronic items")));
    }


    /*
    @Test
    public void testAddCategory() throws Exception {
        // Mocked Category data to simulate the addition of a new category
        CategoryDTO newCategory = new CategoryDTO();
        newCategory.setName("Electronics");
        newCategory.setDescription("All electronic items");
        CategoryDTO savedCategory = new CategoryDTO(1L, "Electronics", "All electronic items");

        // Mock the service method to return the saved category after addition
        when(categoryService.addCategory(newCategory)).thenReturn(savedCategory);

        // Make post request
        mvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Electronics\", \"description\": \"All electronic items\" }"))  // Pass the JSON body
                .andExpect(status().isCreated())  // Verify HTTP 201 Created
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Verify the response is JSON
                .andExpect(jsonPath("$.name", is("Electronics")))  // Verify the name
                .andExpect(jsonPath("$.description", is("All electronic items")))  // Verify the description
                .andExpect(jsonPath("$.categoryId", is(1L)));  // Verify the ID of the saved category
    }

     */

    @Test
    public void testAddCategory() throws Exception {
        CategoryDTO newCategory = new CategoryDTO();
        newCategory.setName("Electronics");
        newCategory.setDescription("All electronic items");
        CategoryDTO returnedCategory = new CategoryDTO(1L, "Electronics", "All electronic items");

        //when(categoryService.addCategory((CategoryDTO) any(CategoryDTO.class))).thenReturn(returnedCategory);
        when(categoryService.addCategory(newCategory)).thenReturn(returnedCategory);

        mvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Electronics\",\"description\":\"All electronic items\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.categoryId", is(1)))
                .andExpect(jsonPath("$.name", is("Electronics")))
                .andExpect(jsonPath("$.description", is("All electronic items")));
    }
}