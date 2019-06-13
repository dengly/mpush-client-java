/*
 * (C) Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     ohun@live.cn (夜色)
 */

package com.mpush.api;


/**
 * Created by ohun on 2016/1/23.
 *
 * @author ohun@live.cn (夜色)
 */
public interface ClientListener {

    /**
     * 连接成功回调
     * @param client
     */
    void onConnected(Client client);

    /**
     * 断开连接回调
     * @param client
     */
    void onDisConnected(Client client);

    /**
     * 握手成功回调
     * @param client
     * @param heartbeat
     */
    void onHandshakeOk(Client client, int heartbeat);

    /**
     * 接收到推送回调
     * @param client
     * @param content
     * @param messageId
     */
    void onReceivePush(Client client, byte[] content, int messageId);

    /**
     * 剔除用户回调
     * @param deviceId
     * @param userId
     */
    void onKickUser(String deviceId, String userId);

    /**
     * 绑定回调
     * @param success
     * @param userId
     */
    void onBind(boolean success, String userId);

    /**
     * 解绑回调
     * @param success
     * @param userId
     */
    void onUnbind(boolean success, String userId);
}
