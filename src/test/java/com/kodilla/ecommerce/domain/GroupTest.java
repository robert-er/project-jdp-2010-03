package com.kodilla.ecommerce.domain;

import com.kodilla.ecommerce.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GroupTest {

    @Autowired
    private GroupRepository groupRepository;

    private final Group group1 = new Group();
    private final Group group2 = new Group();

    @Test
    public void shouldSaveGroupInDatabase() {
        //Given
        group1.setName("test name when saving group");
        //When
        Group savedGroup = groupRepository.save(group1);
        //Then
        assertNotNull(savedGroup.getId());
        //CleanUp
        cleanUp(savedGroup);
    }

    @Test
    public void shouldSetName() {
        //Given
        String givenName = "Test name";
        //When
        group1.setName(givenName);
        Group savedGroup = groupRepository.save(group1);
        //Then
        assertEquals(givenName, savedGroup.getName());
        //CleanUp
        cleanUp(savedGroup);
    }

    @Test
    public void shouldSetDescription() {
        //Given
        String givenDescription = "Test description";
        //When
        group1.setDescription(givenDescription);
        Group savedGroup = groupRepository.save(group1);
        //Then
        assertEquals(givenDescription, savedGroup.getDescription());
        //CleanUp
        cleanUp(savedGroup);
    }

    @Test
    public void shouldSetProducts() {
        //Given
        Product product1 = new Product();
        product1.setTitle("test1");
        product1.setGroup(group1);
        Product product2 = new Product();
        product2.setTitle("test2");
        product2.setGroup(group1);
        Product product3 = new Product();
        product3.setTitle("test3");
        product3.setGroup(group2);
        //When
        group1.getProducts().add(product1);
        group1.getProducts().add(product2);
        group2.getProducts().add(product3);
        Group savedGroup1 = groupRepository.save(group1);
        Group savedGroup2 = groupRepository.save(group2);
        //Then
        assertTrue(savedGroup1.getProducts().contains(product1));
        assertTrue(savedGroup1.getProducts().contains(product2));
        assertTrue(savedGroup2.getProducts().contains(product3));
        //CleanUp
        cleanUp(savedGroup1);
        cleanUp(savedGroup2);
    }

    private void cleanUp(Group savedGroup) {
        groupRepository.deleteById(savedGroup.getId());
    }
}