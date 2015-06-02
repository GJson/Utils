/**
 * @author Gjson
 * @since 2015-5-16 ����7:09:08
 */

package com.clark.ormlite;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import java.sql.SQLException;

public class UserDbHelper extends OrmLiteSqliteOpenHelper {
	private static final String TAG = "DatabaseHelper";
	// ���ݿ�����
	private static final String DATABASE_NAME = "user.db";
	// ���ݿ�version
	private static final int DATABASE_VERSION = 1;

	private Dao<UserTable, Integer> userDao = null;
	private RuntimeExceptionDao<UserTable, Integer> userRuntimeDao = null;

	public UserDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// �����������ļ������� ���ݱ��е㷱������ϲ����
		// super(context, DATABASE_NAME, null, DATABASE_VERSION,
		// R.raw.ormlite_config);
	}

	/**
	 * @param context
	 * @param databaseName
	 * @param factory
	 * @param databaseVersion
	 */
	public UserDbHelper(Context context, String databaseName,
			CursorFactory factory, int databaseVersion) {
		super(context, databaseName, factory, databaseVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase,
			ConnectionSource connectionSource) {
		try {
			// ����User��
			TableUtils.createTable(connectionSource, UserTable.class);
			// ��ʼ��DAO
			userDao = getUserDao();
			userRuntimeDao = getUserDataDao();
		} catch (SQLException e) {
			Log.e(TAG, e.toString());
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase,
			ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, UserTable.class, true);
		} catch (SQLException e) {
			Log.e(TAG, e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	private Dao<UserTable, Integer> getUserDao() throws SQLException {
		if (userDao == null)
			userDao = getDao(UserTable.class);
		return userDao;
	}

	public RuntimeExceptionDao<UserTable, Integer> getUserDataDao() {
		if (userRuntimeDao == null) {
			userRuntimeDao = getRuntimeExceptionDao(UserTable.class);
		}
		return userRuntimeDao;
	}

	/**
	 * �ͷ� DAO
	 */
	@Override
	public void close() {
		super.close();
		userRuntimeDao = null;
	}

}
