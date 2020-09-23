package com.bupt.test;

import com.bupt.dao.IRoleDao;
import com.bupt.dao.IUserDao;
import com.bupt.domain.Role;
import com.bupt.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class RoleTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IRoleDao roleDao;

    @Before //用于在测试方法执行之前执行
    public void init() throws Exception {
        // 1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 3.使用工厂对象，创建SqlSession对象
        sqlSession = factory.openSession();
        // 4.获取代理对象
        roleDao = sqlSession.getMapper(IRoleDao.class);

    }

    @After // 用于在测试方法执行之后执行
    public void destroy() throws Exception {
        // 6.提交事务
        sqlSession.commit();
        // 6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        List<Role> roles = roleDao.findAll();
        for(Role role : roles){
            System.out.println("--------每个角色的信息-------");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }


}
