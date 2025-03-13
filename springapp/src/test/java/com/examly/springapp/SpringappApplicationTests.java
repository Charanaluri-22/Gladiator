package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.lang.reflect.Field;  // Import the Field class

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.examly.springapp.SpringappApplication;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {
	
	 @Autowired
	    private MockMvc mockMvc;


	    @Test
	    public void backend_testCartHasOneToOneAnnotation() {
	        try {
	            // Use reflection to get the Class object for the Course class
	            Class<?> courseClass = Class.forName("com.examly.springapp.model.Cart");

	            // Get all declared fields in the Course class
	            Field[] declaredFields = courseClass.getDeclaredFields();

	            // Check each field for the @OneToOne annotation
	            boolean hasOneToOne = false;
	            for (Field field : declaredFields) {
	                if (field.isAnnotationPresent(OneToOne.class)) {
	                	hasOneToOne = true;
	                    break; // Stop checking once we find one field with @OneToMany
	                }
	            }
		
		
	            // If no field with @OneToMany is found, fail the test
	            if (!hasOneToOne) {
	                fail("No field with @OneToOne annotation found in Cart class.");
	            }

	        } catch (ClassNotFoundException e) {
	            // If the class is not found, fail the test
	            fail("Class not found: " + e.getMessage());
	        }
	    }
	 
    @Test
    public void backend_testOrderHasOneToManyAnnotation() {
        try {
            // Use reflection to get the Class object for the Course class
            Class<?> courseClass = Class.forName("com.examly.springapp.model.Orders");

            // Get all declared fields in the Course class
            Field[] declaredFields = courseClass.getDeclaredFields();

            // Check each field for the @OneToMany annotation
            boolean hasManyToMany = false;
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(ManyToMany.class)) {
                    hasManyToMany = true;
                    break; // Stop checking once we find one field with @OneToMany
                }
            }
	
	
            // If no field with @OneToMany is found, fail the test
            if (!hasManyToMany) {
                fail("No field with @ManyToMany annotation found in Orders class.");
            }

        } catch (ClassNotFoundException e) {
            // If the class is not found, fail the test
            fail("Class not found: " + e.getMessage());
        }
    }

    @Test
    public void backend_testCartInterfaceAndImplementation() {
        try {
            Class<?> interfaceClass = Class.forName("com.examly.springapp.service.CartService");
            Class<?> implementationClass = Class.forName("com.examly.springapp.service.CartServiceImpl");

            assertTrue(interfaceClass.isInterface(), "The specified class is not an interface");
            assertTrue(interfaceClass.isAssignableFrom(implementationClass), "Implementation does not implement the interface");
        } catch (ClassNotFoundException e) {
            fail("Interface or implementation not found");
        }
    }


    
    @Test
    public void backend_testGetAllCourses() throws Exception {
        mockMvc.perform(get("/api/course")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(print())
        .andExpect(content().contentType("application/json"))
	.andExpect(jsonPath("$").isArray())
	.andReturn();
    }
    

    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

	 @Test
   public void backend_testCustomerControllerClassExists() {
       checkClassExists("com.examly.springapp.controller.CustomerController");
   }
	 
	 @Test
	   public void backend_testReviewControllerClassExists() {
	       checkClassExists("com.examly.springapp.controller.ReviewController");
	   }
	 
	 @Test
	   public void backend_testCartModelClassExists() {
	       checkClassExists("com.examly.springapp.model.Cart");
	   }
	 
	 @Test
	   public void backend_testCourseModelClassExists() {
	       checkClassExists("com.examly.springapp.model.Course");
	   }
	 
	 @Test
	   public void backend_testUserModelClassExists() {
	       checkClassExists("com.examly.springapp.model.User");
	   }
	 
	 @Test
	   public void backend_testOrdersModelClassExists() {
	       checkClassExists("com.examly.springapp.model.Orders");
	   }

}
  