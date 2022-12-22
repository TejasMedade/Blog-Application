/**
 * 
 */
package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Category;

/**
 * @author tejas
 *
 */
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
