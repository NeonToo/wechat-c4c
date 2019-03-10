package com.sap.csc.service.wechat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import me.chanjar.weixin.mp.api.WxMpMessageHandler;

/**
 * @author i071053
 */
public abstract class AbstractHandler implements WxMpMessageHandler {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected final Gson gson = new Gson();
}
