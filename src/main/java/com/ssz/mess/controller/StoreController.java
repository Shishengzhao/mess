package com.ssz.mess.controller;

import com.ssz.mess.pojo.Comment;
import com.ssz.mess.pojo.Grade;
import com.ssz.mess.pojo.Store;
import com.ssz.mess.pojo.vo.StoreVo;
import com.ssz.mess.service.IStoreService;
import com.ssz.mess.service.IUserService;
import com.ssz.mess.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StoreController {

    @Autowired
    private IStoreService storeService;

    @Autowired
    private IUserService userService;

    @GetMapping("/stores")
    public String list(ModelMap modelMap){
        List<Store> stores = storeService.getStores();
        //放在请求域中
        modelMap.addAttribute("stores",stores);
        return "store/list";
    }

    //根据店铺号查询
    @PostMapping("/findStore")
    public String findStore(HttpServletRequest request,
                            ModelMap modelMap){
        String storeId = request.getParameter("storeId");
        System.out.println(storeId);
        List<Store> stores = new ArrayList<>();
        if(storeId == "" || !MathUtil.isInteger(storeId)){
            stores = storeService.getStores();
        }else {
            int iid = Integer.parseInt(storeId);
            Store store = storeService.getStoreByStoreId(iid);
            if(store == null){
                String msg = "店铺不存在";
                modelMap.addAttribute("msg",msg);
            }else {
                stores.add(store);
            }
        }
        System.out.println(stores);
        modelMap.addAttribute("stores",stores);
        return "store/list";
    }

    //去店铺添加页面
    @GetMapping("/store")
    public String toAddPage(ModelMap modelMap){
        return "store/add";
    }

    //店铺添加
    @PostMapping("/store")
    public String addStore(Store store, Model model, HttpSession session){
        System.out.println("添加：" + store);
        store.setRole(2);
        //对店铺进行添加验证
        //对账号验证
        int id = store.getId();
        String msg = null;
        if(userService.getUserById(id) != null){
             msg = "账号已经存在";
        }
        if(storeService.getStoreByStoreId(store.getStoreId())!=null){
           msg = "店铺号已经存在";
        }
        if(msg != null){
            model.addAttribute("msg",msg);
            return "store/add";
        }
        storeService.saveStore(store);
        return "redirect:/stores";
    }

    //去店铺修改页面
    @GetMapping("/store/{id}")
    public String toEditPage(@PathVariable Integer id,
                             Model model){
        System.out.println("店的id：" + id);
        Store store = storeService.getStoreById(id);
        System.out.println("修改显示：" + store);
        model.addAttribute("store",store);
        //到修改页面
        return "store/add";
    }

    @PutMapping("/store")
    public String updateStore(Store store){
        //把隐藏没有修改的属性查出来
        int id = store.getId();
        Store s = storeService.getStoreById(id);
        Integer gradeId = s.getGradeId();

        store.setGradeId(gradeId);
        store.setRole(2);

        System.out.println("修改: " + store);
        storeService.updateStore(id,store);
        return "redirect:/stores";
    }

    //店铺删除
    @DeleteMapping("/store/{id}")
    public String deleteStore(@PathVariable("id") Integer id){
        System.out.println("店铺删除");
        storeService.deleteStore(id);
        return "redirect:/stores";
    }

    //去店铺详情页面
    @GetMapping("/store/details/{id}")
    public String toDetailsPage(@PathVariable("id") Integer id,
                                ModelMap modelMap){
        StoreVo store = storeService.getStoreWithOthers(id);
        modelMap.addAttribute("store",store);
        System.out.println(store);
        return "store/details";
    }
}
