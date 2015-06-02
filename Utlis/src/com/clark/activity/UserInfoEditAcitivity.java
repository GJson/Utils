package com.clark.activity;

import com.clark.ormlite.UserDbHelper;
import com.clark.ormlite.UserTable;
import com.clark.utils.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;

import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.List;

public class UserInfoEditAcitivity extends OrmLiteBaseActivity<UserDbHelper>
{
    private TextView mTextView;
    private RuntimeExceptionDao<UserTable, Integer> mUserDAO;

    UserTable user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_edit);

        mUserDAO = getHelper().getUserDataDao();

        mTextView = (TextView)findViewById(R.id.userinfo);

        deleteAll();
        mTextView.append("\n#######Begin to Insert#########\n");
        insertTest();
        display();
        mTextView.append("\n#######Begin to Update#########\n");
        user.setUsername("update");
        update(user);
        display();
        mTextView.append("\n#######Begin to Delete#########\n");
        delete("name2");
        display();
        mTextView.append("\n#######Begin to Search#########\n");
        mTextView.append(search("name1").toString());
    }

    /**
     * ����ֵ����
     */
    private void insertTest()
    {
        for (int i = 0; i < 5; i++)
        {
            user = new UserTable();
            user.setUsername("name" + i);
            user.setPassword("test_pass " + i);
            mUserDAO.createIfNotExists(user);
        }
    }

    /**
     * ����
     * 
     * @param user �����µ�user
     */
    private void update(UserTable user)
    {
        mUserDAO.createOrUpdate(user);
        // mUserDAO.update(user);
    }

    /**
     * ����ָ����id �� username ɾ��һ��
     * 
     * @param id
     * @param username
     * @return ɾ���ɹ�����true ��ʧ�ܷ���false
     */
    private int delete(String username)
    {
        try
        {
            // ɾ��ָ������Ϣ������delete User where 'id' = id ;
            DeleteBuilder<UserTable, Integer> deleteBuilder = mUserDAO.deleteBuilder();
            deleteBuilder.where().eq("username", username);

            return deleteBuilder.delete();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * ����id��ѯuser
     * 
     * @param id
     * @return
     */
    private UserTable search(String username)
    {
        try
        {
            // ��ѯ��query ����ֵ��һ���б�
            // ���� select * from User where 'username' = username;
            List<UserTable> users = mUserDAO.queryBuilder().where().eq("username", username).query();
            if (users.size() > 0)
                return users.get(0);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ɾ��ȫ��
     */
    private void deleteAll()
    {
        mUserDAO.delete(queryAll());
    }

    /**
     * ��ѯ���е�
     */
    private List<UserTable> queryAll()
    {
        List<UserTable> users = mUserDAO.queryForAll();
        return users;
    }

    /**
     * ��ʾ���е�
     */
    private void display()
    {
        List<UserTable> users = queryAll();
        for (UserTable user : users)
        {
            mTextView.append(user.toString());
        }
    }
}
