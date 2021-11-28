package com.stonebridge.itcastmpspringboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stonebridge.itcastmpspringboot.mapper.UserMapper;
import com.stonebridge.itcastmpspringboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ItcastMpSpringbootApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(20);
        user.setMail("trump@itcast.cn");
        user.setName("Trump");
        user.setUserName("Trump");
        user.setPassword("123456");
        int result = this.userMapper.insert(user); //返回的result是受影响的行数，并不是自增后的id
        System.out.println("result = " + result);
        //在MyBatis Plus插件中，当新增数据后，自增长后的id会回填到对象中。此时会生成一个long型的id，这个涉及id的生成策略，后面会将
        System.out.println(user.getId());
    }


    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(7l);
        user.setAge(18);
        user.setUserName("JFK");
        this.userMapper.updateById(user);
    }

    @Test
    public void testUpdate1() {
        User user = new User();
        user.setAge(20); //更新的字段
        user.setPassword("8888888");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "zhangsan"); //匹配user_name = zhangsan 的用户数据
        //根据条件做更新
        int result = this.userMapper.update(user, wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void testUpdate2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        //设置更新的字段的新值
        wrapper.set("age", 21).set("password", "77777").set("user_name", "zhangsan");
        //设置更新的条件，匹配user_name = zhangsan 的用户数据
        wrapper.eq("user_name", "zhangsan");
        int result = this.userMapper.update(null, wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(7l);
        System.out.println("result => " + result);
    }

    @Test
    public void testDeleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("age", 21);
        columnMap.put("name", "张三");
        //将columnMap中的元素设置为删除的条件，多个之间为and关系
        int result = this.userMapper.deleteByMap(columnMap);
        System.out.println("result = " + result);
    }

    @Test
    public void testDelete() {
        //将实体对象进行包装，包装为操作条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name", "jfk").eq("password", "123456");
        int result = this.userMapper.delete(wrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void testDelete1() {
        User user = new User();
        user.setAge(24);
        user.setName("sunqi");
        //将实体对象进行包装，包装为操作条件
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int result = this.userMapper.delete(wrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void testeleteBatchIds() {
        //根据id集合批量删除
        int result = this.userMapper.deleteBatchIds(Arrays.asList(3L, 4L, 5L));
        System.out.println("result = " + result);
    }

    @Test
    public void testSelectById() {
        //根据id查询数据
        User user = this.userMapper.selectById(9L);
        System.out.println("result = " + user);
    }

    @Test
    public void testSelectBatchIds() {
        //根据id集合批量查询
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(2L, 3L, 5L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("password", "123456");
        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 23); //年龄大于23岁
        //根据条件查询数据
        Long count = this.userMapper.selectCount(wrapper);
        System.out.println("count = " + count);
    }

    @Test
    public void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 23); //年龄大于23岁
        wrapper.like("email", "itcast");
        //根据条件查询数据
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectPage() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 19); //年龄大于20岁
        Page<User> page = new Page<>(1, 3);
        //根据条件查询数据
        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数：" + iPage.getTotal());
        System.out.println("数据总页数：" + iPage.getPages());
        System.out.println("当前页数：" + iPage.getCurrent());
        List<User> users = iPage.getRecords();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

}
