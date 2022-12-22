/**
 * 
 */
package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author tejas
 *
 */
@Entity
@Data
public class Role {

	@Id
	private Integer roleId;

	private String roleName;

}
