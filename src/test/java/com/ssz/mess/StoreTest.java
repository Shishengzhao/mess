package com.ssz.mess;

import com.ssz.mess.mapper.StoreMapper;
import com.ssz.mess.pojo.Store;
import com.ssz.mess.pojo.vo.StoreVo;
import com.ssz.mess.service.IStoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreTest {

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private IStoreService storeService;

//    @Test
//    void test(){
//        StoreVo store = storeMapper.getStoreWithOthers(1);
//        List<Menu> menus = store.getMenus();
//        List<Comment> comments = store.getComments();
//        System.out.println("该店的信息如下：");
//        System.out.println("店名；" + store.getName() +
//                "\n店铺号：" + store.getStoreId() +
//                "\n所在楼层：" + store.getFloor());
//        System.out.println("-----------------------");
//        System.out.println("该店的评分内容:");
//        System.out.println(store.getGrade());
//        System.out.println("-----------------------");
//        System.out.println("该店的菜单:");
//        for (Menu menu : menus) {
//            System.out.println(menu);
//        }
//        System.out.println("-----------------------");
//        System.out.println("该店的评论:");
//        for (Comment comment : comments) {
//            System.out.println(comment);
//        }
//    }
    @Test
    void test(){
        StoreVo storeVo = storeService.getStoreWithOthers(1);
        System.out.println("这家店的信息");
        System.out.println(storeVo.getStore());
        System.out.println("-----------------");
        System.out.println("该店的菜单信息");
        System.out.println(storeVo.getMenus());
        System.out.println("-----------------");
        System.out.println("该店的评论");
        System.out.println(storeVo.getComments());
        System.out.println("-----------------");
        System.out.println("该店的评分");
        System.out.println(storeVo.getGrade());
    }

    @Test
    void testStore(){
        Store store = new Store();
        store.setId(10086);
        store.setPassword("123456");
        store.setStoreId(1);
        store.setRole(1);
        store.setName("哈哈哈");
        store.setFloor(1);
        store.setPhone(1231322123);
        store.setRole(2);
        store.setGradeId(10);

        Store s = new Store();
        s.setName("你好啊啊啊啊");
        BeanUtils.copyProperties(s,store);
        System.out.println(store);

    }
    @Test
    void findStore(){
        Store s = storeService.getStoreByStoreId(1);
        Store store = storeMapper.getStoreByStoreId(1);
        System.out.println(s);
        System.out.println(store);
    }
}
