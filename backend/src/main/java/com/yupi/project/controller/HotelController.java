package com.yupi.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.project.common.BaseResponse;
import com.yupi.project.common.ErrorCode;
import com.yupi.project.common.ResultUtils;
import com.yupi.project.exception.BusinessException;
import com.yupi.project.model.entity.Hotel;
import com.yupi.project.service.HotelService;
import com.yupi.project.service.UserService;
import com.yupi.yuapicommon.model.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Resource
    private HotelService hotelService;

    @Resource
    private UserService userService;

    // 1. Get Hotels
    @GetMapping("/list")
    public BaseResponse<List<Hotel>> listHotels(Hotel hotelQuery, HttpServletRequest request) {
        QueryWrapper<Hotel> queryWrapper = new QueryWrapper<>();
        
        // Dynamic query
        if (StringUtils.isNotBlank(hotelQuery.getName())) {
            queryWrapper.like("name", hotelQuery.getName());
        }
        if (StringUtils.isNotBlank(hotelQuery.getStatus())) {
            queryWrapper.eq("status", hotelQuery.getStatus());
        } else {
            // Default logic: Admin sees all, Merchant sees own, Public sees approved
            User loginUser = null;
            try {
                loginUser = userService.getLoginUser(request);
            } catch (Exception e) {
                // Not logged in or anonymous
            }

            if (loginUser != null) {
                if ("admin".equals(loginUser.getUserRole())) {
                    // Admin sees all, no extra filter needed
                } else if ("merchant".equals(loginUser.getUserRole()) || (loginUser.getUserRole() != null && loginUser.getUserRole().contains("merchant"))) {
                    queryWrapper.eq("userId", loginUser.getId());
                } else {
                     queryWrapper.eq("status", "approved");
                }
            } else {
                queryWrapper.eq("status", "approved");
            }
        }
        
        // Support querying by specific owner if admin/public wants to see? 
        // For now, simple implementation logic matching Node.js
        if(hotelQuery.getUserId() != null) {
             queryWrapper.eq("userId", hotelQuery.getUserId());
        }

        List<Hotel> list = hotelService.list(queryWrapper);
        return ResultUtils.success(list);
    }

    // 2. Add / Update Hotel
    @PostMapping("/add")
    public BaseResponse<Hotel> addHotel(@RequestBody Hotel hotel, HttpServletRequest request) {
        if (hotel == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        
        if (hotel.getId() != null && hotel.getId() > 0) {
            // Update
            Hotel oldHotel = hotelService.getById(hotel.getId());
            if (oldHotel == null) {
                throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
            }
            // Check permission
            if (!oldHotel.getUserId().equals(loginUser.getId()) && !userService.isAdmin(request)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
            hotel.setStatus("pending"); // Reset to pending on update
            hotelService.updateById(hotel);
        } else {
            // Create
            hotel.setUserId(loginUser.getId());
            hotel.setStatus("pending");
            hotelService.save(hotel);
        }
        return ResultUtils.success(hotelService.getById(hotel.getId()));
    }

    // 3. Audit
    @PostMapping("/audit")
    public BaseResponse<Boolean> auditHotel(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        Long id = Long.valueOf(params.get("id").toString());
        String status = (String) params.get("status");
        String reason = (String) params.get("reason");
        
        Hotel hotel = new Hotel();
        hotel.setId(id);
        hotel.setStatus(status);
        hotel.setRejectReason(reason);
        
        boolean b = hotelService.updateById(hotel);
        return ResultUtils.success(b);
    }
    
    // 4. Detail
    @GetMapping("/{id}")
    public BaseResponse<Hotel> getHotelById(@PathVariable long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Hotel hotel = hotelService.getById(id);
        if (hotel == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(hotel);
    }
}
