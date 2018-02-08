package org.hfly.springbootdemo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hfly.springbootdemo.entity.Sticker;
import org.hfly.springbootdemo.entity.User;
import org.hfly.springbootdemo.mapper.StickerMapper;
import org.hfly.springbootdemo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StickerService {
	@Resource
	UserMapper userMapper;
	@Resource
	StickerMapper stickerMapper;

	public boolean postSticker(int userId, String text) {
		try {
			stickerMapper.addSticker(userId, System.currentTimeMillis(), text);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Sticker> getAllStickerText() {
		try {
			return stickerMapper.getAllSticker();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Sticker> getIndexStickerTextByTimeAndPage(int page) {
		try {
			List<User> users = userMapper.getAllUser();
			PageHelper.startPage(page, 20);
			PageInfo<Sticker> stickers = new PageInfo<Sticker>(stickerMapper.getStickerByTime());
			return stickers.getList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
