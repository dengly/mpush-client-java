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

package com.mpush.security;



import com.mpush.client.ClientConfig;
import com.mpush.util.crypto.RSAUtils;

import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by ohun on 2015/12/24.
 *
 * 加密方式盒子
 *
 * @author ohun@live.cn (夜色)
 */
public final class CipherBox {
    public int aesKeyLength = ClientConfig.I.getAesKeyLength();
    public static final CipherBox INSTANCE = new CipherBox();
    private SecureRandom random = new SecureRandom();
    private RSAPublicKey publicKey;


    public RSAPublicKey getPublicKey() {
        if (publicKey == null) {
            String key = ClientConfig.I.getPublicKey();
            try {
                publicKey = (RSAPublicKey) RSAUtils.decodePublicKey(key);
            } catch (Exception e) {
                throw new RuntimeException("load public key ex, key=" + key, e);
            }
        }
        return publicKey;
    }

    public byte[] randomAESKey() {
        byte[] bytes = new byte[aesKeyLength];
        random.nextBytes(bytes);
        return bytes;
    }

    public byte[] randomAESIV() {
        byte[] bytes = new byte[aesKeyLength];
        random.nextBytes(bytes);
        return bytes;
    }

    public byte[] mixKey(byte[] clientKey, byte[] serverKey) {
        byte[] sessionKey = new byte[aesKeyLength];
        for (int i = 0; i < aesKeyLength; i++) {
            byte a = clientKey[i];
            byte b = serverKey[i];
            int sum = Math.abs(a + b);
            int c = (sum % 2 == 0) ? a ^ b : b ^ a;
            sessionKey[i] = (byte) c;
        }
        return sessionKey;
    }

    public int getAesKeyLength() {
        return aesKeyLength;
    }

    public RsaCipher getRsaCipher() {
        return new RsaCipher(getPublicKey());
    }
}
