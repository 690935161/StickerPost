package org.hfly.springbootdemo.mapper;

import org.apache.ibatis.annotations.Param;
import org.hfly.springbootdemo.entity.Sticker;

import java.util.List;

public interface StickerMapper {
	public void addSticker(@Param("userId") int userId, @Param("postTime") long postTime, @Param("txt") String txt);

	public List<Sticker> getAllSticker();

	public List<Sticker> getStickerByTime();

	public Sticker getStickerTextById(@Param("stickerId") int stickerId);

	public List<Sticker> getStickerTextByUserId(@Param("userId") int userId);

	public void deleteStickerTextById(@Param("stickerId") int stickerId);

}