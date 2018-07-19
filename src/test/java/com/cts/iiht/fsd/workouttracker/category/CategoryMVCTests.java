package com.cts.iiht.fsd.workouttracker.category;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.iiht.fsd.workouttracker.WorkoutTrackerApplication;
import com.cts.iiht.fsd.workouttracker.category.controller.CategoryController;
import com.cts.iiht.fsd.workouttracker.category.service.CategoryService;
import com.cts.iiht.fsd.workouttracker.category.to.WorkoutCategoryTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WorkoutTrackerApplication.class)
@AutoConfigureMockMvc
public class CategoryMVCTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private WorkoutCategoryTO categoryTO;
	
	@Mock
	private CategoryService categoryService;
	
	@InjectMocks
	private CategoryController categoryController = new CategoryController();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(categoryTO);
		assertNotNull("Category service injection failed.", categoryService);
	}
	
	private static String asJsonStringConvert(final Object obj){
		try{
			return new ObjectMapper().writeValueAsString(obj);
		}catch(Exception ex){
			throw new  RuntimeException(ex);
		}
	}
	
	@Test
	public void testGetAllCategories() throws Exception{
		WorkoutCategoryTO categoryTO1 = new WorkoutCategoryTO();
		categoryTO1.setCategoryId(1L);
		categoryTO1.setCategoryName("Category 1");
		WorkoutCategoryTO categoryTO2 = new WorkoutCategoryTO();
		categoryTO2.setCategoryId(2L);
		categoryTO2.setCategoryName("Category 2");
		when(categoryService.getCategoryList()).thenReturn(Arrays.asList(categoryTO1,categoryTO2));
		
		mockMvc.perform(get("/workouttracker/categories/").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].categoryName", is("Category 1")))
				.andExpect(jsonPath("$[1].categoryName", is("Category 2")));
		verify(categoryService, times(1)).getCategoryList();
	}
	
	@Test
	public void testGetCategoryById() throws Exception {
		WorkoutCategoryTO categoryTO1 = new WorkoutCategoryTO();
		categoryTO1.setCategoryId(11L);
		categoryTO1.setCategoryName("Category 11");
		when(categoryService.getCategoryById(1L)).thenReturn(categoryTO1);
		
		mockMvc.perform(get("/workouttracker/categories/11"))
				.andExpect(status().isOk());
		verify(categoryService, times(1)).getCategoryById(11L);
	}
	
	@Test
	public void testSaveCategory() throws Exception {
		WorkoutCategoryTO categoryTO1 = new WorkoutCategoryTO();
		categoryTO1.setCategoryId(null);
		categoryTO1.setCategoryName("Category 10");
		
		when(categoryService.getCategoryById(10L)).thenReturn(null);
		when(categoryService.saveCategory(categoryTO1)).thenReturn(10L);
		
		mockMvc.perform(post("/workouttracker/categories/").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonStringConvert(categoryTO1)))
					.andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateCategory() throws Exception {
		WorkoutCategoryTO categoryTO1 = new WorkoutCategoryTO();
		categoryTO1.setCategoryId(10L);
		categoryTO1.setCategoryName("Category 10 New Name");
		
		when(categoryService.getCategoryById(10L)).thenReturn(categoryTO1);
		when(categoryService.saveCategory(categoryTO1)).thenReturn(10L);
		
		mockMvc.perform(put("/workouttracker/categories/").contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonStringConvert(categoryTO1)))
					.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteCategory() throws Exception{
		WorkoutCategoryTO categoryTO1 = new WorkoutCategoryTO();
		categoryTO1.setCategoryId(10L);
		categoryTO1.setCategoryName("Category 10 New Name");
		
		when(categoryService.getCategoryById(10L)).thenReturn(categoryTO1);
		when(categoryService.deleteCategory(10L)).thenReturn(true);
		when(categoryService.getCategoryById(10L)).thenReturn(null);
		
		mockMvc.perform(delete("/workouttracker/categories/10").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
				
	}
}
