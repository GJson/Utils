
/**
 * @author Gjson
 * @since 2015-5-16 ����7:09:08
 */
package com.clark.ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * ���ݿ��Ӧ��pojo�࣬ע��һ������ 1����д�������� @DatabaseTable 
 * 2����д���г־û���� @DatabaseField
 * ����ʹ˳������������ 3������һ���޲εĹ��캯��
 */
// ������
@DatabaseTable(tableName = "user")
public class UserTable {
	// ���� id ������
	@DatabaseField(generatedId = true)
	private int id;
	// ӳ��
	@DatabaseField(canBeNull = false)
	private String username;
	// ��Ϊ��
	@DatabaseField(canBeNull = false)
	private String password;

	@DatabaseField(defaultValue = "")
	private String nickname;

	public UserTable() {
		// ORMLite ��Ҫһ���޲ι���
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		String text = "";

		text += "\nid = " + id;
		text += "\nusername = " + username;
		text += "\npassword = " + password;
		return text;
	}

}