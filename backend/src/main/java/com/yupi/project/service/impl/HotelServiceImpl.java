package com.yupi.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.project.mapper.HotelMapper;
import com.yupi.project.model.entity.Hotel;
import com.yupi.project.service.HotelService;
import org.springframework.stereotype.Service;

/**
 * 酒店服务实现
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel>
    implements HotelService {

}
